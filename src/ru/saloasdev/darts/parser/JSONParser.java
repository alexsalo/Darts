package ru.saloasdev.darts.parser;


import java.io.Serializable;

import org.json.JSONObject;

import ru.saloasdev.darts.exception.BusinessException;


public interface JSONParser<T extends Serializable> extends Serializable {

	public T parse(JSONObject jsonObject) throws BusinessException;
}
