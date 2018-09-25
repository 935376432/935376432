#include <stdio.h>
#include "com_shterm_sg_server_jni_SystemJNI.h"
#include <stdbool.h>
extern int yy_open(void**);
extern int yy_temperature_read(void *, int, int *);
extern int yy_irigb_read(void *, int *, int *);
extern int yy_irigb_write(void *, int);
extern int yy_ledpanel_write(void *, int);
extern int yy_ledpanel_read(void *, int *);
extern int yy_power_stat_read(void *, int *);
JNIEXPORT jlong JNICALL Java_com_shterm_sg_server_jni_SystemJNI_shtermOpen(JNIEnv *env, jobject obj) {
        long devPointer;
        int Result = yy_open(&devPointer);
        if (Result == 0)
                return devPointer;
        return 0;
}
JNIEXPORT jint JNICALL Java_com_shterm_sg_server_jni_SystemJNI_shtermLedPanelWrite(JNIEnv *env, jobject obj, jint status, jlong dev) {
        void* devPointer = (void *)dev;
        //int openResult = yy_open(&devPointer);
        if (devPointer == NULL)
                return -1;
        return  yy_ledpanel_write(devPointer, status);
}
JNIEXPORT jint JNICALL Java_com_shterm_sg_server_jni_SystemJNI_shtermLedPanelRead(JNIEnv *env, jobject obj, jlong dev) {
        void* devPointer = dev;
        if (devPointer == NULL)
                return -1;
        //int openResult = yy_open(&devPointer);
        int ledStatus = -1;
        int ledReadResult = yy_ledpanel_read(devPointer, &ledStatus);
        return ledStatus;
}
JNIEXPORT jint JNICALL Java_com_shterm_sg_server_jni_SystemJNI_shtermTemperatureRead(JNIEnv *env, jobject obj, jlong dev) {
        void* devPointer = dev;
        //int openResult = yy_open(&devPointer);
        if (devPointer == NULL)
                return -1;
        int devTemper = -1;
        int temperReadResult = yy_temperature_read(devPointer, 0, &devTemper);
        return devTemper;
}
JNIEXPORT jintArray JNICALL Java_com_shterm_sg_server_jni_SystemJNI_shtermIrigbRead(JNIEnv *env, jobject obj, jlong dev) {
        void* devPointer = dev;
        if (devPointer == NULL)
                return -1;
        //int openResult = yy_open(&devPointer);
        int enable = -1;
        int fault = -1;
        yy_irigb_read(devPointer, &enable, &fault);
        jint buf[2];
        buf[0] = enable;
        buf[1] = fault;
        jintArray resultArray = (*env)->NewIntArray(env, 2);
        (*env)->SetIntArrayRegion(env, resultArray, 0, 2, buf);
        return resultArray;
}
JNIEXPORT jint JNICALL Java_com_shterm_sg_server_jni_SystemJNI_shtermIrigbWrite(JNIEnv *env, jobject obj, jint enable, jlong dev) {
        void* devPointer = dev;
        if (devPointer == NULL)
                return -1;
        //int openResult = yy_open(&devPointer);
        int result = yy_irigb_write(devPointer, enable);
        return result;
}
JNIEXPORT jint JNICALL Java_com_shterm_sg_server_jni_SystemJNI_shtermPowerStatRead(JNIEnv *env, jobject obj, jlong dev) {
        void* devPointer = dev;
        if (devPointer == NULL)
                return -1;
        int powerStat = -1;
        int result = yy_power_stat_read(devPointer, &powerStat);
        return powerStat;
}

/*
 * Class:     com_test_SystemJNI
 * Method:    shtermEventRead
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_test_SystemJNI_shtermEventRead(JNIEnv *, jobject, jlong){
		void* devPointer = dev;
		char* event;
		int size = 0;
        //int yy_event_read(void *dev, char *event, int *size);
		
		
		if (yy_event_read(devPointer, event, size) == 0){
			return event;
		} else {
			return null;
		}
}
#endif
#endif

























