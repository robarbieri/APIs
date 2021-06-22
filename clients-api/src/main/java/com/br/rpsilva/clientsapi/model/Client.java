package com.br.rpsilva.clientsapi.model;

import lombok.*;

@Getter // Cria automaticamente os getters
@Setter // Cria automaticamente os setters
@AllArgsConstructor // Cria automaticamente o construtor com todas as propriedades
public class Client
{
  private String name;
  private String phone;
  private int age;
}