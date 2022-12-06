package top.nextnet.resource;


import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.jboss.resteasy.annotations.jaxrs.PathParam;


import java.util.Collection;


@Path("/vendor")
@RegisterRestClient(configKey = "vendor-api")
public interface VendorService {


    @Path("{idVendor}/venue/{idVenue}/quota")
    @GET
    RemainingQuota getQuotas(@PathParam int idVendor, @PathParam int idVenue);

    @Path("{idVendor}/venues")
    @GET
    Collection<Gig> getGigs(@PathParam int idVendor);
}
