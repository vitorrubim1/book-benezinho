package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.PessoaFisica;
import br.com.fiap.domain.service.PessoaFisicaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.Objects;

@Path("/pf")
public class PessoaFisicaResource implements Resource<PessoaFisica, Long>{
    @Context
    UriInfo uriInfo;

    private PessoaFisicaService service = new PessoaFisicaService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response findAll() {
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response findById(@PathParam("id") Long id) {
        PessoaFisica pf = service.findById(id);

        if (Objects.isNull(pf)) return Response.status(404).build();

        return Response.ok(pf).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response persist(PessoaFisica body) {
        PessoaFisica persist = service.persist(body);

        UriBuilder uriInfoAbsolutePathBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriInfoAbsolutePathBuilder.path(String.valueOf(persist.getId())).build();

        return Response.created(uri).entity(pf).build();;
    }
}
