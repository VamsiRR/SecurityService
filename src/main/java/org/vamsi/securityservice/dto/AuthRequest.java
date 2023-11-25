package org.vamsi.securityservice.dto;

import java.io.Serializable;

public record AuthRequest(String email, String password) implements Serializable { }
