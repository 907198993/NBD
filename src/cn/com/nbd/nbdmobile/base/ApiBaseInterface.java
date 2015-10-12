package cn.com.nbd.nbdmobile.base;

public enum ApiBaseInterface {

	
	/**
	 * �ļ��ϴ�
	 */
	UPLOAD_FILE("Upload_file"),
	
	/**
	 * ԭʼ�ļ��ϴ�
	 */
	UPLOAD_FORM_FILE("Upload_form_file"),
	
	/**
	 * 修改头像
	 */
	UPLOAD_USER_PHOTO("upload_user_photo"),
	
	/**
	 * 修改用户信息
	 */
	MODIFY_USER_MSG("menber_update"),

	/**
	 * �̳Ƕ�����ַ
	 */
	MALL_GET_PAY("get_shop_pay"),
	
	/**
	 * �Ź�������ַ
	 */
	TUAN_GET_PAY("get_tuan_pay"),
	
	/**
	 * ע��
	 */
	REGISTER("register"),
	
	
	/**
	 * ��½���˳�
	 */
	INDEX("index");
	
	private String name;
	
	private ApiBaseInterface(String name){
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
