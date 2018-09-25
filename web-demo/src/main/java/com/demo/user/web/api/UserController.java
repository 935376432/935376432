/*
 *jiji java
 */
package com.demo.user.web.api;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.user.entity.User;
import com.demo.user.repository.UserRepository;
import com.demo.user.service.UserService;

@RestController
@RequestMapping(path = "api/user")
public class UserController {

    /** ��־��¼���� */
    private Log log = LogFactory.getLog(UserController.class);

    /** testҵ���߼��ӿ� */
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;




    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getList (){

        userRepository.findAll();

        log.info("get user");
        return ResponseEntity.ok(userRepository.findAll());
    }

    /**
     * ��ҳ��ȡ�û���Ϣ
     * @param pageable ��ҳ��Ϣ
     * @return �û���Ϣ
     */
    @RequestMapping(path = "/userPage",method = RequestMethod.GET)
    public ResponseEntity<?> getListPage (Pageable pageable){

        Page<User> users = userRepository.findAll(pageable);

        log.info("get user");
        log.debug("get user");
        return ResponseEntity.ok(users);
    }

    /*@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getTest (HttpServletRequest request,Principal principal,Pageable pageable){
        userRepository.findAll();
        return ResponseEntity.ok(userRepository.findAll());
    }*/

    /**
     * �½������û�
     * @param request ������Ϣ
     * @param principal �����û���Ϣ
     * @param user �û���Ϣ
     * @return �û���Ϣid
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createUser (HttpServletRequest request,Principal principal,@RequestBody User user){

        User rtnUser = userService.createUser(user);

        return ResponseEntity.ok(rtnUser.getId());
    }

    /*@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createUser (){

        //User rtnUser = userService.createUser(user);

        return ResponseEntity.ok("aa");
    }*/

    /**
     * �޸ı����û�
     * @param request ������Ϣ
     * @param principal �����û���Ϣ
     * @param user �û���Ϣ
     * @return �û���Ϣid
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser (HttpServletRequest request,Principal principal,@RequestBody User user){

        User rtnUser = userService.updateUser(user);

        return ResponseEntity.ok(rtnUser.getId());
    }

    /**
     * �޸ı����û�
     * @param request ������Ϣ
     * @param principal �����û���Ϣ
     * @param user �û���Ϣ
     * @return �û���Ϣid
     */
    @RequestMapping(path = "{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser (HttpServletRequest request,Principal principal,@PathVariable("id") User user){

        User rtnUser = userService.deleteUser(user);

        return ResponseEntity.ok(rtnUser.getId());
    }


}
