/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.2.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package net.openvpn.ovpn3;

public class ClientAPI_OpenVPNClientHelper {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected ClientAPI_OpenVPNClientHelper(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ClientAPI_OpenVPNClientHelper obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(ClientAPI_OpenVPNClientHelper obj) {
    long ptr = 0;
    if (obj != null) {
      if (!obj.swigCMemOwn)
        throw new RuntimeException("Cannot release ownership as memory is not owned");
      ptr = obj.swigCPtr;
      obj.swigCMemOwn = false;
      obj.delete();
    }
    return ptr;
  }

  @SuppressWarnings({"deprecation", "removal"})
  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ovpncliJNI.delete_ClientAPI_OpenVPNClientHelper(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public ClientAPI_OpenVPNClientHelper() {
    this(ovpncliJNI.new_ClientAPI_OpenVPNClientHelper(), true);
  }

  public ClientAPI_MergeConfig merge_config(String path, boolean follow_references) {
    return new ClientAPI_MergeConfig(ovpncliJNI.ClientAPI_OpenVPNClientHelper_merge_config(swigCPtr, this, path, follow_references), true);
  }

  public ClientAPI_MergeConfig merge_config_string(String config_content) {
    return new ClientAPI_MergeConfig(ovpncliJNI.ClientAPI_OpenVPNClientHelper_merge_config_string(swigCPtr, this, config_content), true);
  }

  public ClientAPI_EvalConfig eval_config(ClientAPI_Config config) {
    return new ClientAPI_EvalConfig(ovpncliJNI.ClientAPI_OpenVPNClientHelper_eval_config(swigCPtr, this, ClientAPI_Config.getCPtr(config), config), true);
  }

  public static int max_profile_size() {
    return ovpncliJNI.ClientAPI_OpenVPNClientHelper_max_profile_size();
  }

  public static boolean parse_dynamic_challenge(String cookie, ClientAPI_DynamicChallenge dc) {
    return ovpncliJNI.ClientAPI_OpenVPNClientHelper_parse_dynamic_challenge(cookie, ClientAPI_DynamicChallenge.getCPtr(dc), dc);
  }

  public String crypto_self_test() {
    return ovpncliJNI.ClientAPI_OpenVPNClientHelper_crypto_self_test(swigCPtr, this);
  }

  public static String platform() {
    return ovpncliJNI.ClientAPI_OpenVPNClientHelper_platform();
  }

  public static String copyright() {
    return ovpncliJNI.ClientAPI_OpenVPNClientHelper_copyright();
  }

}
