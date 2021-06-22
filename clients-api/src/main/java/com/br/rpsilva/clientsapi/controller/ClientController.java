package com.br.rpsilva.clientsapi.controller;

import java.util.ArrayList;
import java.util.List;

import com.br.rpsilva.clientsapi.model.Client;
import org.springframework.web.bind.annotation.*;

// @RestController determina que controller terá saída em JSON
@RestController
public class ClientController {
  private List<Client> clients = new ArrayList<>(); // Lista que guardará os clientes

  // @GetMapping determina que o endpoint '/clientes' só estará acessível para GET
  @GetMapping(value = "/clients") // O método buscaClientes() será invocado quando recebermos acesso no endpoint '/clientes'
  public List<Client> buscaClientes() {

    // Cria objetos clientes
    Client client1 = new Client("Renan", "98507-1229", 26);
    Client client2 = new Client("Joao", "98230-0239", 38);

    // Alimenta lista de clientes
    clients.add(client1);
    clients.add(client2);

    // Retorna lista de clientes
    return clients;
  }
}