
REPLACE INTO `Villager` (`level`, `name`) VALUES (1, 'Titi'), (1, 'Toto');

REPLACE INTO `BankAccount` (`balance`, `ownerId`) VALUES (6000,1), (59, 2);

REPLACE INTO `Wishlist` (`villagerId`, `shape`, `color`) VALUES (1,'carre', 'rouge'), (2, 'rond', 'bleu');

REPLACE INTO `Inventory` (`idVillager`, `inventoryCapacity`) VALUES (1,0), (2,1);