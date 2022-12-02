REPLACE INTO `Villager` (`level`, `name`) VALUES (1, 'Titi'), (1, 'Toto');

REPLACE INTO `BankAccount` (`balance`, `ownerId`) VALUES (100,1), (59, 2);
REPLACE INTO `Loan` (`idVillager`, `loanAmount`, `loanStatus`) VALUES (1, 120, 'WAITING');