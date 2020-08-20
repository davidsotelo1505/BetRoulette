package com.masivian.roulettebets.service;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masivian.roulettebets.ServiceException;
import com.masivian.roulettebets.model.Bet;
import com.masivian.roulettebets.model.Roulette;
import com.masivian.roulettebets.model.User;
import com.masivian.roulettebets.repository.BetRepository;
import com.masivian.roulettebets.repository.RouletteRepository;
import com.masivian.roulettebets.repository.UserRepository;

@Service(value = "betService")
public class BetServiceImpl implements BetService {
	final Logger log = LoggerFactory.getLogger(this.getClass());
	Random randNumber = new Random();
	String[] strings = { "negro", "rojo" };
	@Autowired
	private BetRepository betRepository;
	@Autowired
	private RouletteRepository roueletteRepository;
	@Autowired
	private UserRepository userRepository;;
	@Override
	public Bet save(Bet bet) throws ServiceException {
		log.info("Init save bet");
		try {
			User user = userRepository.getFindById(bet.getUser().getId());
			Roulette aux = roueletteRepository.getFindById(bet.getRoulette_id());
			if (!aux.getStatus().equals("close")) {
				if(user.getCredit() >= bet.getAmount() && bet.getAmount()<= 10000) {
					bet.setNumber_bet(randNumber.nextInt((36 - 0) + 1) + 0);
					String random = (strings[new Random().nextInt(strings.length)]);
					bet.setColor_bet(random);
					bet.setStatus("active");
					User userAux= userRepository.getFindById(user.getId());
					userAux.setCredit(user.getCredit()-bet.getAmount());
					userRepository.save(userAux);
					bet = betRepository.save(bet);
					
					return bet;
				}
				throw new ServiceException("Mount no valid or credit insufficients");
			}
			throw new ServiceException("Roulette is close");	
		} catch (Exception e) {
			if(e instanceof ServiceException) {
				throw (ServiceException)e;
			}
			throw new ServiceException("The bet is no posible");
		}	
	}
	@Override
	public List<Bet> findAll() throws ServiceException {
		
		return betRepository.findAll();
	}
	@Override
	public Bet getfindById(Long id) throws ServiceException {

		return betRepository.getFindById(id);
	}
}