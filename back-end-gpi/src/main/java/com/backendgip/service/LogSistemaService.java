//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.LogSistema;
import java.util.List;

public interface LogSistemaService {
	List<LogSistema> getLogs();

	LogSistema saveLog(LogSistema log);

	void deleteLog(LogSistema log);

	LogSistema getLogById(Integer id);
}
