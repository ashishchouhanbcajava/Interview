package com.Learning.Learning.Enums;

import java.util.Set;

public enum Role {

	USER(Set.of(Permission.Read)), ADMIN(Set.of(Permission.All));

	private Set<Permission> permissions;

	Role(Set<Permission> permissions) {
		// TODO Auto-generated constructor stub
		this.permissions = permissions;
	}

	public Set<Permission> getPermissions() {
		return this.permissions;
	}
}
