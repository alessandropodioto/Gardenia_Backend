package com.betacom.pr.services.interfaces;

import java.util.List;

import com.betacom.pr.dto.inputs.LoginReq;
import com.betacom.pr.dto.inputs.UserReq;
import com.betacom.pr.dto.outputs.LoginDTO;
import com.betacom.pr.dto.outputs.UserDTO;


public interface IUserServices {
	
	void create(UserReq req) throws Exception;
	void update(UserReq req) throws Exception;
	void delete(String userName) throws Exception;
	
	LoginDTO login(LoginReq req) throws Exception;
	List<UserDTO> list();
	UserDTO getByUserName(String userName) throws Exception;

}
