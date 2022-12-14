package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.VillagerDAO;
import fr.pantheonsorbonne.ufr27.miage.model.Villager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collection;

@ApplicationScoped
public class VillagerServiceImpl implements VillagerService {

    @Inject
    VillagerDAO villagerDAO;

    @Override
    public Collection<Villager> listVillager() {
        return villagerDAO.listAllVillager();
    }

    @Override
    @Transactional
    public Boolean levelUpVillager(fr.pantheonsorbonne.ufr27.miage.dto.Villager villager) {
        villagerDAO.levelUpVillager(villager.getId());
        return true;
    }

    @Override
    @Transactional
    public void banVillager(fr.pantheonsorbonne.ufr27.miage.dto.Villager villager, boolean banStatus) {
        villagerDAO.banVillager(villager.getId(), banStatus);
    }
}