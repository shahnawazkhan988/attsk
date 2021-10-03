package com.example.attsk.dao;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import com.example.attsk.entities.Users;

@Repository
public interface IUsersDao extends JpaRepository<Users, Long>
{
	 
	Users findByuserMatricola(String matricola);

	

}
