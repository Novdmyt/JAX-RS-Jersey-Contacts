package org.example.app.service;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.app.entity.Contact;


import java.net.URI;
import java.util.*;

@Path("/api/v1.0/contacts")
@Produces({MediaType.APPLICATION_JSON})
public class ContactService {

    private static final List<Contact> contacts;

    static {
        contacts = new ArrayList<>();
        contacts.add(new Contact(1L, "Alice", "+380 587 471-9857"));
        contacts.add(new Contact(2L, "Bob", "369 258 157-2587"));
        contacts.add(new Contact(3L, "Lucy", "+380 569 547-6985"));
        contacts.add(new Contact(4L, "Tom", "+380 587 547-8564"));

    }

    @GET
    public List<Contact> getContacts() {
        return contacts;
    }

    @GET
    @Path("{id: [0-9]}")
    public Contact getContact(@PathParam("id") Long id) {
        Contact contact = new Contact(id, null, null);
        int index = Collections.binarySearch(contacts, contact, Comparator.comparing(Contact::getId));
        if (index >= 0)
            return contacts.get(index);
        else
            throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createContact(Contact contact) {
        if (Objects.isNull(contact.getId()))
            throw new WebApplicationException(Response.Status.BAD_REQUEST);

        int index = Collections.binarySearch(contacts, contact, Comparator
                .comparing(Contact::getId));

        if (index < 0) {
            contacts.add(contact);
            return Response.status(Response.Status.CREATED)
                    .location(URI.create(String.format("/api/v1.0/contacts/%s", contact.getName()))).build();
        } else
            throw new WebApplicationException(Response.Status.CONFLICT);
    }

    @PUT
    @Path(MediaType.APPLICATION_JSON)
    public Response updateContact(@PathParam("id") Long id, Contact contact) {
        contact.setId(id);
        int index = Collections.binarySearch(contacts, contact, Comparator.comparing(Contact::getId));
        if (index >= 0) {
            Contact updatedContact = contacts.get(index);

            updatedContact.setPhone(contact.getPhone());
            contacts.set(index, updatedContact);
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        } else
            throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @DELETE
    @Path("{id: [0-9]+}")
    public Response deleteContact(@PathParam("id") Long id) {
        Contact contact = new Contact(id, null, null);
        int index = Collections.binarySearch(contacts, contact, Comparator.comparing(Contact::getId));

        if (index >= 0) {
            contacts.remove(index);
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        } else
            throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
}


