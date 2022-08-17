package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.LikeConverter;
import actions.views.LikeView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.JpaConst;
import models.Like;

public class LikeService extends ServiceBase {


    public LikeView findOne(int id) {
        return LikeConverter.toView(findOneInternal(id));
    }

    private Like findOneInternal(int id) {
        return em.find(Like.class, id);
    }

    public void create(LikeView lv) {
        LocalDateTime ldt = LocalDateTime.now();
        lv.setCreatedAt(ldt);
        lv.setUpdatedAt(ldt);
        createInternal(lv);
        }

private void createInternal(LikeView lv) {

    em.getTransaction().begin();
    em.persist(LikeConverter.toModel(lv));
    em.getTransaction().commit();

}
public List<LikeView> getMinePerPage(ReportView rv, int page) {

    List<Like> likes = em.createNamedQuery("like.getAllMine", Like.class)
            .setParameter("report", ReportConverter.toModel(rv))
            .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
            .setMaxResults(JpaConst.ROW_PER_PAGE)
            .getResultList();
    return LikeConverter.toViewList(likes);
}


public long countAllMinn(ReportView rv) {
    long likes_count = (long) em.createNamedQuery("like.countAllMine", Long.class)
            .setParameter("report", ReportConverter.toModel(rv))

            .getSingleResult();
    return likes_count;
}

public long countAllMine(ReportView rv, EmployeeView ev) {
    long likes_count = (long) em.createNamedQuery("like.countAll", Long.class)
            .setParameter("employee", EmployeeConverter.toModel(ev))
            .setParameter("report", ReportConverter.toModel(rv))
            .getSingleResult();
    return likes_count;
}
}







