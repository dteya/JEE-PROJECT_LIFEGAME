package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.VillagerDAO;
import fr.pantheonsorbonne.ufr27.miage.model.Villager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.logging.Logger;

@ApplicationScoped
public class VillagerServiceImpl implements VillagerService {

    @Inject
    VillagerDAO villagerDAO;

    private static final Logger LOGGER = Logger.getLogger("logger");

    @Override
    public Collection<Villager> listVillager() {
        return villagerDAO.listAllVillager();
    }

    @Override
    @Transactional
    public Boolean levelUpVillager(fr.pantheonsorbonne.ufr27.miage.dto.Villager villager) {
        villagerDAO.levelUpVillager(villager.getId());
        LOGGER.info("Villager #" + villager.getId() + " upgraded his level");
        return true;
    }

    @Override
    @Transactional
    public void banVillager(fr.pantheonsorbonne.ufr27.miage.dto.Villager villager, boolean banStatus) {
        villagerDAO.banVillager(villager.getId(), banStatus);
        LOGGER.info("Villager #" + villager.getId() + " ban status is now " + banStatus);
    }
}