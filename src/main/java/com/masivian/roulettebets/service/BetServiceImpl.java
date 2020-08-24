package com.masivian.roulettebets.service;

import java.util.List;

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
				if (user.getCredit() >= bet.getAmount() && bet.getAmount() <= 10000) {
					bet.setColor_bet_user(bet.getColor_bet_user());
					bet.setNumber_bet_user(bet.getNumber_bet_user());
					validateBet(bet);
					bet.setStatus("active");
					User userAux = userRepository.getFindById(user.getId());
					userAux.setCredit(user.getCredit() - bet.getAmount());
					userRepository.save(userAux);
					bet = betRepository.save(bet);

					return bet;
				}
				throw new ServiceException("Mount no valid or credit insufficients");
			}
			throw new ServiceException("Roulette is close");
		} catch (Exception e) {
			if (e instanceof ServiceException) {
				throw (ServiceException) e;
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

	public static void validateBet(Bet bet) throws ServiceException {
		String betColor = bet.getColor_bet_user();
		if (null != bet.getNumber_bet_user() && null != bet.getColor_bet_user()) {
			int betNumber = bet.getNumber_bet_user();
			if (betColor.equals("rojo") || betColor.equals("negro")) {
				bet.setColor_bet_user(betColor);
			} else {
				throw new ServiceException("The color must be ROJO or NEGRO");
			}
			if (betNumber >= 0 && betNumber <= 36) {
				if (betNumber % 2 == 0 && !betColor.equals("rojo")) {
					throw new ServiceException("Even numbers must be red");
				}
				if (betNumber % 2 != 0 && !betColor.equals("negro")) {
					throw new ServiceException("Odd numbers must be negro");
				}
				bet.setNumber_bet_user(betNumber);
			} else {
				throw new ServiceException("The number must be between 0 and 36");
			}
		} else if (null != bet.getNumber_bet_user()) {
			int betNumber = bet.getNumber_bet_user();
			if (betNumber >= 0 && betNumber <= 36) {
				bet.setNumber_bet_user(betNumber);
			} else {
				throw new ServiceException("The number must be between 0 and 36");
			}
		} else if(null != bet.getColor_bet_user()){
			if (betColor.equals("rojo") || betColor.equals("negro")) {
				bet.setColor_bet_user(betColor);
			} else {
				throw new ServiceException("The color must be ROJO or NEGRO");
			}
		}else {
			throw new ServiceException("The color and number cant be null");
		}
		
	}

}