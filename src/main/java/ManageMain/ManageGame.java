/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMain;

/**
 *
 * @author vyzac
 */
import DBMain.Game;
import java.util.List; 
import java.util.Date;
import java.util.Iterator; 
 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class ManageGame {
    
    private static SessionFactory factory; 
   public static void main(String[] args) {
   
      try{
         factory = new Configuration().configure().buildSessionFactory();
      }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
      ManageGame ME = new ManageGame();
      
//      ME.listScores();


//      ME.updateScore();


//      ME.deleteScore();

      ME.listScores();
   }
   /* Method to CREATE a player in the database */
   public Integer addScore(String email, int point){
      Session session = factory.openSession();
      Transaction tx = null;
      Integer gameID = null;
      try{
         tx = session.beginTransaction();
         Game game = new Game(email,  point);
         gameID = (Integer) session.save(game); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return gameID;
   }
   /* Method to  READ all the players */
   public void listScores( ){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         List players = session.createQuery("FROM Score").list(); 
         for (Iterator iterator = 
                           players.iterator(); iterator.hasNext();){
            Game game = (Game) iterator.next();
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
   
/* Method to read a single player */
   public Game listScore( String uname){
      Session session = factory.openSession();
      Transaction tx = null;
      Game game=null;
      int id=0;
      try{
         tx = session.beginTransaction();
         List gid = session.createQuery("select max(id) from score").list();
         for (Iterator iterator = 
                           gid.iterator(); iterator.hasNext();){
            id = (Integer) iterator.next();
         }
         List player = session.createQuery("select * from score where username="+uname).list();
         for (Iterator iterator = 
                           player.iterator(); iterator.hasNext();){
            game = (Game) iterator.next();
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      if(!(game.getEmail().equals(null))){
          return game;
      }
      else{
          addScore(uname, 0);
          game.setEmail(uname);
          game.setPoint(0);
          game.setId(id+1);
          
      }
      return game;
   }
   
   /* Method to UPDATE score for a player */
   public void updateScore(Integer gameID, int point ){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Game game = 
                    (Game)session.get(Game.class, gameID); 
         game.setPoint(point);
		 session.update(game); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
   /* Method to DELETE a player from the records */
   public void deleteScore(Integer gameID){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Game game = 
                   (Game)session.get(Game.class, gameID); 
         session.delete(game); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }}
