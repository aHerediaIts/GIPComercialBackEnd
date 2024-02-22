//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.model.EmpleadoRol;
import com.backendgip.model.Proyecto;
import com.backendgip.model.Rol;
import com.backendgip.service.EmpleadoRolService;
import com.backendgip.service.EmpleadoService;
import com.backendgip.service.EstadoPropuestaService;
import com.backendgip.service.EtapaProyectoService;
import com.backendgip.service.MailService;
import com.backendgip.service.RolService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping({ "/api/mail" })
public class MailController {
	@Autowired
	private MailService mailService;
	@Autowired
	private EstadoPropuestaService estadoPropuestaService;
	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private EmpleadoRolService empleadoRolService;
	@Autowired
	private EtapaProyectoService etapaService;
	@Autowired
	private RolService rolService;

	public MailController() {
	}

	@PostMapping({ "/proyecto/{idCreator}" })
	public ResponseEntity<?> sendMail(@RequestBody Proyecto proyecto, @PathVariable int idCreator) {
		proyecto.setEtapa(this.etapaService.getEtapaById(proyecto.getEtapa().getId()));
		proyecto.setEstadoPropuesta(this.estadoPropuestaService.getEstadoById(proyecto.getEstadoPropuesta().getId()));
		proyecto.setNombre("REG-" + proyecto.getEtapa().getEtapa() + "-" + proyecto.getCliente().getNomenclatura() + "-"
				+ proyecto.getCodigo());
		String ca;
		String MsgPRP;
		if (proyecto.getEtapa().getEtapa().equalsIgnoreCase("PRP")) {
			ca = "<style>\r\n    table {\r\n      border-collapse: collapse;\r\n      width: 100%;\r\n      border: solid #ccc 1px;\r\n    }\r\n    th, td {\r\n    text-align: center;\r\n    padding: 8px;\r\n    }\r\n    tr:nth-child(even){background-color: #f2f2f2}\r\n    th {\r\n      background-color: #59c3ec;\r\n      color: white;\r\n    }\r\n    </style>\r\n<body>\r\n";
			MsgPRP = " ha creado el proyecto " + proyecto.getNombre() + "-" + proyecto.getDescripcion()
					+ ". <br><br>    <table><thead>\r\n        <tr>\r\n        <th>Proyecto</th>\r\n        <th>"
					+ proyecto.getNombre() + "-" + proyecto.getDescripcion()
					+ "</th>\r\n    </tr>\r\n    </thead>\r\n    <tbody>\r\n        <tr>\r\n          <td> Cliente </td>\r\n          <td>"
					+ proyecto.getCliente().getNombre()
					+ "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> codigo Del Proyecto </td>\r\n          <td>"
					+ proyecto.getCodigo()
					+ "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Con Etapa </td>\r\n          <td>(PRP) Propuesta Real De Proyecto </td>\r\n        </tr>\r\n        <tr>\r\n          <td> Tipo De Proyecto </td>\r\n          <td>"
					+ proyecto.getTipo().getTipo()
					+ "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Estado De La Propuesta </td>\r\n          <td>"
					+ proyecto.getEstadoPropuesta().getEstado()
					+ "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Estado Del Proyecto </td>\r\n          <td>"
					+ proyecto.getEstadoProyecto().getEstado()
					+ "</td>\r\n        </tr>\r\n        </tbody>\r\n      </table>\r\n    \r\n</body>\r\n</html>\r\n";
			List<EmpleadoRol> LGerentes = this.empleadoRolService.findByRol(this.rolService.findById(4));

			for (int i = 0; i < LGerentes.size(); ++i) {
				if (((EmpleadoRol) LGerentes.get(i)).getEmpleado().getId().equals(idCreator)) {
					this.mailService.sendSimpleMail(((EmpleadoRol) LGerentes.get(i)).getEmpleado().getEmail(),
							"CREACION DE PROYECTO " + proyecto.getNombre() + "-" + proyecto.getDescripcion(),
							ca + "Se" + MsgPRP);
				} else {
					this.mailService.sendSimpleMail(((EmpleadoRol) LGerentes.get(i)).getEmpleado().getEmail(),
							"CREACION DE PROYECTO " + proyecto.getNombre() + "-" + proyecto.getDescripcion(),
							ca + this.empleadoService.getEmpleadoById(idCreator).getNombre() + MsgPRP);
				}

				if (!((Rol) this.rolService.findRolesByEmpleado(this.empleadoService.getEmpleadoById(idCreator)).get(0))
						.getId().equals(4)) {
					this.mailService.sendSimpleMail(this.empleadoService.getEmpleadoById(idCreator).getEmail(),
							"CREACION DE PROYECTO " + proyecto.getNombre() + "-" + proyecto.getDescripcion(),
							ca + "Se" + MsgPRP);
				}
			}
		} else if (proyecto.getEtapa().getEtapa().equalsIgnoreCase("CRN")) {
			ca = "<style>\r\n    table {\r\n      border-collapse: collapse;\r\n      width: 100%;\r\n      border: solid #ccc 1px;\r\n    }\r\n    th, td {\r\n    text-align: center;\r\n    padding: 8px;\r\n    }\r\n    tr:nth-child(even){background-color: #f2f2f2}\r\n    th {\r\n      background-color: #59c3ec;\r\n      color: white;\r\n    }\r\n    </style>\r\n<body>\r\n";
			MsgPRP = " ha creado el proyecto " + proyecto.getNombre() + "-" + proyecto.getDescripcion() + " <br><br> ";
			String MsgCRN = "    <table><thead>\r\n        <tr>\r\n        <th>Proyecto</th>\r\n        <th>"
					+ proyecto.getNombre() + "-" + proyecto.getDescripcion()
					+ "</th>\r\n    </tr>\r\n    </thead>\r\n    <tbody>\r\n        <tr>\r\n          <td> Cliente </td>\r\n          <td>"
					+ proyecto.getCliente().getNombre()
					+ "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> codigo Del Proyecto </td>\r\n          <td>"
					+ proyecto.getCodigo()
					+ "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Con Etapa </td>\r\n          <td>("
					+ proyecto.getEtapa().getEtapa()
					+ ") Caso Real De Negocio </td>\r\n        </tr>\r\n        <tr>\r\n          <td> Tipo De Proyecto </td>\r\n          <td>"
					+ proyecto.getTipo().getTipo()
					+ "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Estado De La Propuesta </td>\r\n          <td>"
					+ proyecto.getEstadoPropuesta().getEstado()
					+ "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Estado Del Proyecto </td>\r\n          <td>"
					+ proyecto.getEstadoProyecto().getEstado()
					+ "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Horas Aprobadas </td>\r\n          <td>"
					+ proyecto.getHorasPlaneadas()
					+ "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Valor Aprobado Cliente </td>\r\n          <td>"
					+ proyecto.getCosto()
					+ "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Componente </td>\r\n          <td>"
					+ proyecto.getComponente().getComponente()
					+ "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> lider Del Proyecto </td>\r\n          <td>"
					+ proyecto.getLider().getNombre()
					+ "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Fecha De Aprobacion </td>\r\n          <td>"
					+ proyecto.getFechaAprobacion()
					+ "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Fecha De Inicio </td>\r\n          <td>"
					+ proyecto.getFechaInicio()
					+ "</td>\r\n        </tr>\r\n        </tr>\r\n        <tr>\r\n          <td> Director Asignado Cliente </td>\r\n          <td>"
					+ proyecto.getDirectorClient()
					+ "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Director Asignado ITS </td>\r\n          <td>"
					+ proyecto.getDirectorIts().getNombre()
					+ "</td>\r\n        </tr>\r\n        </tbody>\r\n      </table>\r\n    \r\n</body>\r\n</html>\r\n";
			this.mailService.sendSimpleMail(proyecto.getLider().getEmail(),
					"ASIGNADO A PROYECTO " + proyecto.getNombre() + "-" + proyecto.getDescripcion(),
					"Buen Dia.<br><br> Usted a sido asignado como lider al proyecto " + proyecto.getNombre() + "-"
							+ proyecto.getDescripcion() + "<br><br>" + ca + MsgCRN);
			List<EmpleadoRol> LGerentes = this.empleadoRolService.findByRol(this.rolService.findById(4));

			for (int i = 0; i < LGerentes.size(); ++i) {
				if (((EmpleadoRol) LGerentes.get(i)).getEmpleado().getId() == idCreator) {
					this.mailService.sendSimpleMail(((EmpleadoRol) LGerentes.get(i)).getEmpleado().getEmail(),
							"CREACION DE PROYECTO " + proyecto.getNombre() + "-" + proyecto.getDescripcion(),
							ca + "Se" + MsgPRP + MsgCRN);
				} else {
					this.mailService.sendSimpleMail(((EmpleadoRol) LGerentes.get(i)).getEmpleado().getEmail(),
							"CREACION DE PROYECTO " + proyecto.getNombre() + "-" + proyecto.getDescripcion(),
							ca + this.empleadoService.getEmpleadoById(idCreator).getNombre() + MsgPRP + MsgCRN);
				}
			}

			if (!((Rol) this.rolService.findRolesByEmpleado(this.empleadoService.getEmpleadoById(idCreator)).get(0))
					.getId().equals(4)) {
				this.mailService.sendSimpleMail(this.empleadoService.getEmpleadoById(idCreator).getEmail(),
						"CREACION DE PROYECTO " + proyecto.getNombre() + "-" + proyecto.getDescripcion(),
						ca + "Se" + MsgPRP + MsgCRN);
			}
		}

		Map<String, Boolean> response = new HashMap();
		response.put("MAIL SENT", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
