/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.resources;

import com.ab811.webapps2020.entity.Conversion;
import java.sql.Timestamp;
import javax.ejb.Startup;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ab811
 */
@Path("timestamp")
@Startup
public class Timestamper {
    
    /**
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response conversion(){
        
        Timestamp t = new Timestamp(System.currentTimeMillis());
    
            
        return Response.ok(t).build();
        
    }
}
