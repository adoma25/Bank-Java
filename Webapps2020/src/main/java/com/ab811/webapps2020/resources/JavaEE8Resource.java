package com.ab811.webapps2020.resources;

import com.ab811.webapps2020.entity.Conversion;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("conversion")
public class JavaEE8Resource {
    
    private final double gbpTOusd = 1.38;
    private final double gbpTOeur = 1.16;
    private final double usdTOgbp = 0.72;
    private final double usdTOeur = 0.84;
    private final double eurTOgbp = 0.86;
    private final double eurTOusd = 1.19;
    
    /**
     *
     * @return
     */
    @GET
    public Response ping(){
        return Response.accepted().build();
    }
    
    /**
     *
     * @param currency1
     * @param currency2
     * @param amount
     * @return
     */
    @GET
    @Path("/{currency1}/{currency2}/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response conversion(@PathParam("currency1") String currency1,@PathParam("currency2") String currency2,@PathParam("amount") double amount){
        
        double conversion =0;
        String c1 = currency1.toUpperCase();
        String c2 = currency2.toUpperCase();
        if(null != c1)switch (c1) {
            case "GBP":
                if("USD".equals(c2)){
                    conversion = amount*gbpTOusd;
                    Conversion c = new Conversion(c1,c2,conversion);
                    return Response.ok(c).build();
                    
                }else if("EUR".equals(c2)){
                    conversion = amount*gbpTOeur;
                    Conversion c = new Conversion(c1,c2,conversion);
                    return Response.ok(c).build();
                }   break;
            case "USD":
                if("GBP".equals(c2)){
                    conversion = amount*usdTOgbp;
                    Conversion c = new Conversion(c1,c2,conversion);
                    return Response.ok(c).build();
                }else if("EUR".equals(c2)){
                    conversion = amount*usdTOeur;
                    Conversion c = new Conversion(c1,c2,conversion);
                    return Response.ok(c).build();
                }   break;
            case "EUR":
                if("GBP".equals(c2)){
                    conversion = amount*eurTOgbp;
                    Conversion c = new Conversion(c1,c2,conversion);
                    return Response.ok(c).build();
                }else if("USD".equals(c2)){
                    conversion = amount*eurTOusd;
                    Conversion c = new Conversion(c1,c2,conversion);
                    return Response.ok(c).build();
                }   break;
            default:
                return Response.status(400).build();
        }
    
            
        return Response.status(400, "Wrong currency " + c1 + c2).build();
        
    }
}
