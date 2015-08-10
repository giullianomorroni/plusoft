package com.giullianomorroni.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.giullianomorroni.resource.ErrorResponse;
import com.giullianomorroni.resource.Template;
import com.giullianomorroni.service.TemplateAction;
import com.giullianomorroni.service.TemplateService;

@Path("/template")
public class TemplateController {

	private TemplateService service;

	public TemplateController() {
		service = new TemplateService();
	}
	
	@GET
	@Path("/")
	@Produces(value=MediaType.APPLICATION_JSON)
	public Response template() {
		try {
			List<Template> templates = service.run(TemplateAction.LIST).getTemplates();
			return Response.ok(templates).build();
		} catch (Exception e) {
			return Response.status(500).entity(new ErrorResponse(e)).build();
		}
	}

	@GET
	@Path("/{id}")
	@Produces(value=MediaType.APPLICATION_JSON)
	public Response template(@PathParam("id") String id) {
		try {
			Template template = new Template();
			template.setId(id);
			template = service.run(template, TemplateAction.FIND).getTemplate();
		return Response.ok(template).build();
		} catch (Exception e) {
			return Response.status(500).entity(new ErrorResponse(e)).build();
		}
	}
 
	@POST
	@Path("/")
	@Produces(value=MediaType.APPLICATION_JSON)
	@Consumes(value=MediaType.APPLICATION_JSON)
	public Response template(Template template) {
		try {
			template = this.service.run(TemplateAction.CREATE).getTemplate();
			return Response.ok(template).build();
		} catch (IllegalArgumentException e) {
			return Response.status(412).entity(this.service.getErrorResponse()).build();
		} catch (Exception e) {
			return Response.ok(new ErrorResponse(e)).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Produces(value=MediaType.APPLICATION_JSON)
	@Consumes(value=MediaType.APPLICATION_JSON)
	public Response template(@PathParam("id") Long id, Template template) {
		try {
			template = this.service.run(TemplateAction.UPDATE).getTemplate();
			return Response.ok(template).build();
		} catch (Exception e) {
			return Response.ok(new ErrorResponse(e)).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(value=MediaType.APPLICATION_JSON)
	@Consumes(value=MediaType.APPLICATION_JSON)
	public Response remove(@PathParam("id") Long id) {
		try{
			this.service.run(TemplateAction.DELETE);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.ok(new ErrorResponse(e)).build();
		}
	}

}
