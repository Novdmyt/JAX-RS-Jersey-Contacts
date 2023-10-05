package org.example.app.service;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.app.entity.Contact;


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


}


