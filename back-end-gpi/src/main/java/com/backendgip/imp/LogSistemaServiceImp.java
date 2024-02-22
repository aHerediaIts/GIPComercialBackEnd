//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.LogSistema;
import com.backendgip.repository.LogSistemaRepository;
import com.backendgip.service.LogSistemaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogSistemaServiceImp implements LogSistemaService {
	@Autowired
	private LogSistemaRepository logRepository;

	public LogSistemaServiceImp() {
	}

	public List<LogSistema> getLogs() {
		return (List) this.logRepository.findAll();
	}

	public LogSistema saveLog(LogSistema log) {
		String shortDescription = log.getDescripcion()
				.substring(0, Math.min(255, log.getDescripcion().length()) );
		log.setDescripcion(shortDescription);
		return this.logRepository.save(log);
	}

	public void deleteLog(LogSistema log) {
		this.logRepository.delete(log);
	}

	public LogSistema getLogById(Integer id) {
		return (LogSistema) this.logRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado log con id: " + id);
		});
	}
}
