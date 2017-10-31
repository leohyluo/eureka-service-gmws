package com.ebm.gmws.common.config;


public enum RedisKeys {
	

	MAIN_SYMPTOM_ALL("MainSymptom-all"),
	MAIN_SYMPTOM_OBJECT("MainSymptom-id-$v");		//近期检查肝肾功能是否正常?(对于用药安全很重要哦)

	public static final String VAL_TARGET = "$v";
	
    private String value;

    RedisKeys(String value) {
        this.value = value;
    }

    public int getOrdinal() {
        return this.ordinal();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public static String parse(RedisKeys key, String val) {
    	if(key.value.contains(VAL_TARGET)) {
    		key.value.replace(VAL_TARGET, val);
    	}
    	return key.value;
    }
    
    public static RedisKeys findByValue(String value) {
		for(RedisKeys item : values()) {
			if(item.value.equals(value)) {
				return item;
			}
		}
		return null;
	}
}
