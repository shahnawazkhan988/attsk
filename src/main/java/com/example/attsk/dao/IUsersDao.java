package com.example.attsk.dao;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import com.example.attsk.model.*;

@Repository
public interface IUsersDao extends JpaRepository<UsersDto, Long>
{
	 
	 UsersDto findByuserMatricola(String matricola);

	

}
