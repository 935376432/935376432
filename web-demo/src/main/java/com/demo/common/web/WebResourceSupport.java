/*
 *jiji java
 */
package com.demo.common.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.FileSystems;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *  合并js文件
 */

public class WebResourceSupport implements Filter {

    /** 日志记录对象 */
    private Log log = LogFactory.getLog(WebResourceSupport.class);

    /** 保存合并后的js app 文件 */
    private String combinedAppFileName = "resources/app/appcombine";

    /** 需要合并js文件的路径 */
    private File jsSourceRoot = null;

    /** 保存合并js 文件的路径 */
    private File combinedAppPath = null;

    private JSChangeWatcher jsChangeWatcher;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        log.info("WebResourceSupport task start");
        File appFile = new File(filterConfig.getServletContext().getRealPath(combinedAppFileName));
        combinedAppPath = appFile.getParentFile();
        jsSourceRoot = new File(filterConfig.getServletContext().getRealPath("/resources"));

        combineAllJS();

        jsChangeWatcher = new JSChangeWatcher();
        jsChangeWatcher.start();
        log.info("WebResourceSupport task end");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        if (jsChangeWatcher != null) {
            jsChangeWatcher.shutdown();
            jsChangeWatcher = null;
        }

    }

    /**
     * 处理所有js文件
     */
    public void combineAllJS() {

        try {
            final File combinedThirdPartyFile = combineThirdPartyJavascripts();
            log.info("Function combineAllJS :" + combinedThirdPartyFile);
        } catch (Exception e) {
            log.info("Combine combinedThirdPartyFile error." ,e);
        }

    }

    /**
     * 合并第三方js文件
     *
     * @return 返回js文件
     * @throws IOException 异常
     */
    @SuppressWarnings({ "unchecked" })
    public File combineThirdPartyJavascripts() throws IOException {
        long t0 = System.currentTimeMillis();
        String thirdpartyName = "thirdpartycombine";
        File combinedAppFile = new File(combinedAppPath, thirdpartyName + ".js");
        File combinedAppDataFile = new File(combinedAppPath, thirdpartyName + ".dat");
        int len = jsSourceRoot.getCanonicalPath().length() + 1;
        List<File> jsFiles = new ArrayList<>();
        listThirdPartyJavaScriptFiles(jsSourceRoot, jsFiles);
        Map<String, long[]> filesMap = new HashMap<>();
        if (combinedAppDataFile.exists()) {
            // 检查之前的配置，确认是否发生过变更
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(combinedAppDataFile));
                filesMap = (Map<String, long[]>) ois.readObject();
            } catch (Exception e) {
                log.info("JS file intput error .",e);
                // 文件已经破损，尝试删除app data 文件
                combinedAppDataFile.deleteOnExit();
            } finally {
                IOUtils.closeQuietly(ois);
            }
        }
        boolean needRebuild = false;
        for (File jsFile : jsFiles) {
            long[] finfo = filesMap.remove(jsFile.getCanonicalPath().substring(len));
            if (finfo == null || finfo[0] != jsFile.length() || finfo[1] != jsFile.lastModified()) {
                needRebuild = true;
                break;
            }
        }
        if (!needRebuild && filesMap.size() > 0) {
            needRebuild = true;
        }
        if (!needRebuild && !combinedAppFile.exists()) {
            needRebuild = true;
        }

        if (needRebuild) {
            filesMap.clear();
            log.info("Rebuild " + combinedAppFile);
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(combinedAppFile), "UTF-8"));
                for (File jsFile : jsFiles) {
                    String jf = jsFile.getCanonicalPath().substring(len);
                    log.info("Combine js file :" + jf);
                    bw.write("// " + jf + IOUtils.LINE_SEPARATOR);
                    BufferedReader br = null;
                    try {
                        br = new BufferedReader(new InputStreamReader(
                            new FileInputStream(jsFile), "UTF-8"));
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            bw.write(line + IOUtils.LINE_SEPARATOR);
                        }
                    } finally {
                        IOUtils.closeQuietly(br);
                    }
                    filesMap.put(jsFile.getCanonicalPath().substring(len),
                        new long[] { jsFile.length(), jsFile.lastModified() });
                }
            } catch (Exception e) {
                log.info("Rebuild " + combinedAppFile + " error",e);
            } finally {
                IOUtils.closeQuietly(bw);
            }
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(new FileOutputStream(combinedAppDataFile));
                oos.writeObject(filesMap);
            } catch (Exception e) {
                log.info("ObjectOutputStream error .",e);
            } finally {
                IOUtils.closeQuietly(oos);
            }

            log.info(String.format("Combine thirdParty javascript files successed in %d ms",
                System.currentTimeMillis() - t0));
        }
        return combinedAppFile;
    }

    /**
     * 将特定目录下的所有javascript文件取出并返回。递归所有的子目录。 <br>
     * 该方法确保返回结果中，上层路径的js文件总是在子目录的js文件之前。
     *
     * @param root 根路径
     * @param result 保存结果的列表
     */
    private void listThirdPartyJavaScriptFiles(File root, List<File> result) {
        // result.add(new File(root,"js/string.js"));
        result.add(new File(root, "verdors/jquery/3.2.1/jquery.js"));
        result.add(new File(root, "verdors/angularjs/angular.js"));
        result.add(new File(root, "verdors/angularjs-router/angular1/angular_1_router.js"));
        result.add(new File(root, "verdors/angularjs-router/angular1/ng_route_shim.js"));
        result.add(new File(root, "verdors/lodash/lodash.js"));
        result.add(new File(root, "verdors/restangular/restangular.min.js"));
        result.add(new File(root, "verdors/underscore-1.8.3/underscore-min.js"));
        result.add(new File(root, "verdors/angularjs-router/0.2.18/angular-ui-router.js"));
        result.add(new File(root, "app/app.js"));
        result.add(new File(root, "app/components/main.js"));
        result.add(new File(root, "app/components/demo/haerbin.js"));
        result.add(new File(root, "app/components/demo/hangzhou.js"));
        result.add(new File(root, "app/components/demo/shanghai.js"));
        result.add(new File(root, "app/components/demo/king.js"));
        result.add(new File(root, "app/components/demo/king/first.js"));
        result.add(new File(root, "app/components/demo/king/second.js"));
        result.add(new File(root, "app/components/demo/king/kingmenu.js"));
        result.add(new File(root, "app/components/demo/templateRenderAsset.js"));
        //result.add(new File(root, "verdors/bootstrap/js/bootstrap.min.js"));
        result.add(new File(root, "verdors/jquery-confirm-master/dist/jquery-confirm.min.js"));
        result.add(new File(root, "verdors/bootstrap-3.3.7-dist/js/bootstrap.js"));
        result.add(new File(root, "verdors/d3/d3.js"));
        result.add(new File(root, "verdors/echarts/echarts.js"));

    }

    /**
     * 检测js文件的变化
     */
    class JSChangeWatcher extends Thread {

        private boolean running;

        public JSChangeWatcher() {
            super();
            setName("JSChangeWatcher");
            setDaemon(true);
            running = true;
        }

        public void shutdown() {
            running = false;
            interrupt();
        }

        @Override
        public void run() {
            try {
                WatchService watcher = FileSystems.getDefault().newWatchService();
                Kind<?>[] events = { StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY };
                log.info("Moitoring " + jsSourceRoot);
                jsSourceRoot.toPath().register(watcher, events);
                File[] dirs = jsSourceRoot.listFiles(pathname -> {
                    return pathname.isDirectory();
                });

                while (dirs != null && dirs.length > 0) {
                    List<File> list = new ArrayList<>();
                    for (File dir : dirs) {
                        dir.toPath().register(watcher, events);
                        log.info("Watching directory: " + dir);
                        File[] fileArray = dir.listFiles(pathname -> {
                            return pathname.isDirectory();
                        });

                        if (fileArray != null) {
                            list.addAll(Arrays.asList(fileArray));
                        }
                    }
                    dirs = list.toArray(new File[0]);
                }

                while (running) {
                    WatchKey watchKey = watcher.take();
                    boolean jsChanged = false;
                    for (WatchEvent<?> event : watchKey.pollEvents()) {
                        String file = event.context().toString();
                        if (file.endsWith(".js")) {
                            log.info("JS file changed: " + file);
                            jsChanged = true;
                        }
                    }
                    if (jsChanged) {
                        log.info("recombine JS files");
                        combineAllJS();
                    }
                    watchKey.reset();
                }
            } catch (Exception e) {
                log.info("WatchService js file error .",e);
            }
        }
    }

}
