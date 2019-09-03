#include <jni.h>
#include <string>
// #include <android/log.h>

#define APP_NAME "Benchmarks-Native"

extern "C" JNIEXPORT jstring JNICALL
Java_com_gplio_benchmarks_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}


/*
 * Class:     com_gplio_benchmarks_JavaHTest
 * Method:    bubbleSortInt
 * Signature: ([I)[I
 */
extern "C" JNIEXPORT jintArray JNICALL
Java_com_gplio_benchmarks_LogcatBenchmarkActivity_bubbleSortInt
        (JNIEnv *env, jobject obj, jintArray jnumbers) {

    jint *numbers = (*env).GetIntArrayElements(jnumbers, nullptr);
    jsize len = (*env).GetArrayLength(jnumbers);

    // __android_log_print(ANDROID_LOG_VERBOSE, APP_NAME, "Len :: %d", len);

    while (true) {
        bool swapped = false;
        for (int i = 0; i < len - 1; i+=1) {
            if (numbers[i] > numbers[i + 1]) {
                int t = numbers[i + 1];
                numbers[i + 1] = numbers[i];
                numbers[i] = t;
                swapped = true;
            }
        }
        if (!swapped) {
            break;
        }
    }
    (*env).ReleaseIntArrayElements(jnumbers, numbers, 0);
    return jnumbers;
}
