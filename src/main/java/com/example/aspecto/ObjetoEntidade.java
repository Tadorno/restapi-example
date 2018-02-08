package com.example.aspecto;

import java.util.Date;

public interface ObjetoEntidade<T> {

	public T getId();
	
	public boolean isExcluido();
	
	public Date getDataExclusao();
}
