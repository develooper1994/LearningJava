// interface EventListener {} // empty interface - tagging interface
interface Sports {
   void setHomeTeam(String name);
   void setVisitingTeam(String name);
}

interface Football extends Sports {
   void homeTeamScored(int points);
   void visitingTeamScored(int points);
   void endOfQuarter(int quarter);
}

interface Hockey extends Sports {
   void homeGoalScored();
   void visitingGoalScored();
   void endOfPeriod(int period);
   void overtimePeriod(int ot);
}

interface Event {
   void organize();
}

class HockeyDemo implements Hockey, Event {

   public void setHomeTeam(String name) {
      System.out.println("Home team: " + name);
   }

   public void setVisitingTeam(String name) {}

   public void homeGoalScored() {}

   public void visitingGoalScored() {}

   public void endOfPeriod(int period) {}

   public void overtimePeriod(int ot) {}

   public void organize() {
      System.out.println("Match organized. ");
   }

   public static void main(String[] args) {
      HockeyDemo hockeyDemo = new HockeyDemo();
      hockeyDemo.setHomeTeam("India");
      hockeyDemo.organize();
   }
}
