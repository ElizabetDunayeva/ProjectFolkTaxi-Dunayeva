package com.netcracker.services;

import com.netcracker.entities.Route;
import com.netcracker.entities.Schedule;
import com.netcracker.entities.User;
import com.netcracker.repositories.RouteRepository;
import com.netcracker.repositories.ScheduleRepository;
import com.netcracker.repositories.UserRepository;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//import com.vividsolutions.jts.geom.Coordinate;
//import com.vividsolutions.jts.geom.Point;

@Service
public class RouteService {

    private static final Logger LOG = LoggerFactory.getLogger(RouteService.class);

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private UserRepository userRepository;

    public ArrayList<Route> getRoutesByCityId(Long cityId){

        return routeRepository.findRouteByCity(cityId);
    }
    public Route getRoutesBy(Long routeId){

        return routeRepository.findRouteByRouteId(routeId);
    }
    public void saveNewRoute(Route route, Schedule schedule) {
        LOG.info("[ saveNewRoute : {}", route);

        // получение данных о сессии
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userRepository.findUserByEmail(userDetail.getUsername());

        route.setDriverId(user);
        route.setCity(user.getCityId());
        schedule.setRouteId(route);
        routeRepository.save(route);
        scheduleRepository.save(schedule);
    }

    public void saveNewOneRoute(Route route) {
        LOG.info("[ saveNewOneRoute : {}", route);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userRepository.findUserByEmail(userDetail.getUsername());

        route.setDriverId(user);
        route.setCity(user.getCityId());
        routeRepository.save(route);
    }
    public boolean joinToRoute(long id) {
        LOG.info("[ joinToRoute : {}", id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userRepository.findUserByEmail(userDetail.getUsername());

        Route route =  routeRepository.findRouteByRouteId(id);
        for (Route item:
                user.getRoutes()) {
            if (item.getRouteId() == id) return false;
        }
        if (route.getCountOfPlaces() == 0) {
            return false;
        } else {
            route.setCountOfPlaces(route.getCountOfPlaces() - 1);
            Collection<User> usersInGroup =  route.getUsers();
            usersInGroup.add(user);
            route.setUsers(usersInGroup);
            routeRepository.save(route);
            return true;
        }
    }
    public Double getDriverRatingById(long id) {
        Route route = routeRepository.findRouteByRouteId(id);
        return route.getDriverId().getDriverRating();
    }

    public Collection<User> getAllUserRoute(long id) {
        return routeRepository.findRouteByRouteId(id).getUsers();
    }
}
