package ru.saloasdev.darts.service;

import java.io.Serializable;

import ru.saloasdev.darts.exception.BusinessException;
import ru.saloasdev.darts.exception.TransportException;

import android.content.Context;


public interface HttpCommand extends Serializable {
	
	public Serializable execute(Context context) throws BusinessException, TransportException;
}
