package com.betacom.pr.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.pr.dto.inputs.LoginReq;
import com.betacom.pr.dto.inputs.UserReq;
import com.betacom.pr.dto.outputs.LoginDTO;
import com.betacom.pr.dto.outputs.UserDTO;
import com.betacom.pr.enums.Roles;
import com.betacom.pr.exceptions.WebServiceExceptions;
import com.betacom.pr.models.User;
import com.betacom.pr.repositories.IAddressRepository;
import com.betacom.pr.repositories.IUserRepository;
import com.betacom.pr.services.interfaces.IMessaggioServices;
import com.betacom.pr.services.interfaces.IUserServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserImpl implements IUserServices {

	private final IUserRepository usR;
	private final IAddressRepository addR;
	private final IMessaggioServices msgS;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(UserReq req) throws Exception {
		log.debug("create {}", req);
		if(usR.existsById(req.getUserName()))
			throw new WebServiceExceptions(msgS.get("user_exist")); 
		User us = new User();
		us.setUserName(req.getUserName());
		us.setFirstName(req.getFirstName());
		us.setLastName(req.getLastName());
		us.setEmail(req.getEmail());
		us.setPhone(req.getPhone());
		us.setPassword(req.getPassword());
		us.setRole(Roles.valueOf(req.getRole()));
		//us.setIdAddress(addR.findById(req.getIdAddress().get())); //DA SBLOCCARE QUANTO è PRONTO ADDRESS

		usR.save(us);

	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(UserReq req) throws Exception {
		log.debug("update {}", req);

		User us = usR.findById(req.getUserName()) 
				.orElseThrow(() -> new WebServiceExceptions(msgS.get("user_ntfnd"))); //user_ntfnd l'ho messo prima nella tabella messaggi_systema su DBeaver manualmente


		if(req.getEmail() != null)
			us.setEmail(req.getEmail());
		if(req.getPhone() != null)
			us.setPhone(req.getPhone());
		if(req.getPassword() != null)
			us.setPassword(req.getPassword());
		if(req.getRole() != null)
			us.setRole(Roles.valueOf(req.getRole()));
		//		if(req.getIdAddress() != null)
		//			us.setIdAddress(addR.findById(req.getIdAddress()).get());

		usR.save(us);

	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(String userName) throws Exception {
		log.debug("delete {}", userName);
		User us = usR.findById(userName) 
				.orElseThrow(() -> new WebServiceExceptions(msgS.get("user_ntfnd"))); 

		usR.delete(us);

	}



	@Override
	public List<UserDTO> list() {
		log.debug("list");
		List<User> lU = usR.findAll();

		return lU.stream()
				.map((u -> UserDTO.builder()
						.userName(u.getUserName())
						.firstName(u.getFirstName())
						.lastName(u.getLastName())
						.email(u.getEmail())
						.phone(u.getPhone())
						.password(u.getPassword())
						.role(u.getRole().toString())
						.idAddresses(u.getAddresses().stream().map(a -> a.getId()).toList())
						.build())
						).toList();
	}

	@Override
	public UserDTO getByUserName(String userName) throws Exception {
		log.debug("getByUserName {}", userName);
		User u = usR.findById(userName) 
				.orElseThrow(() -> new WebServiceExceptions(msgS.get("user_ntfnd"))); 
		
		return UserDTO.builder()
				.userName(u.getUserName())
				.firstName(u.getFirstName())
				.lastName(u.getLastName())
				.email(u.getEmail())
				.phone(u.getPhone())
				.password(u.getPassword())
				.role(u.getRole().toString())
				.idAddresses(u.getAddresses().stream().map(a -> a.getId()).toList())

				.build();
	}

	@Override
	public LoginDTO login(LoginReq req) throws Exception {
				User us = usR.findById(req.getUserName()) 
				.orElseThrow(() -> new WebServiceExceptions(msgS.get("login_invalid"))); 
		
		if (!us.getPassword().equals(req.getPassword()))
			throw new WebServiceExceptions(msgS.get("login_invalid"));
		
		return LoginDTO.builder()
				.id(us.getUserName())
				.role(us.getRole().toString())
				.build();	
	}

}
