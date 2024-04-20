package esercitazione4.bet.esercizio4_2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.*;
import java.util.concurrent.TimeUnit;
import static esercitazione4.bet.esercizio4_2.Logging.*;

public class Race extends Thread {

    static final String multicastGroup = "230.0.0.1";
    static final int SERVER_MULTICAST_PORT = 8002;
    static final int MULTICAST_DELAY = 5;

    private int raceId;
    private boolean active = false;
    private Date startTime;
    private Date startBetting;
    private List<Bet> bets;

    Race(int raceId, Date startTime) {
        this.raceId = raceId;
        this.startTime = startTime;
        this.startBetting = calculateStartBetting(startTime);
        this.bets = new LinkedList<>();
    }

    boolean placeBet(Bet bet) {
        if (!isActive()) {
            bets.add(bet);
            return true;
        }
        return false;
    }

    int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date calculateStartBetting(Date startTime) {
        Calendar temp = Calendar.getInstance();
        temp.setTime(startTime);
        temp.add(Calendar.HOUR, -1);
        return temp.getTime();
    }

    public Date getStartBetting() {
        return startBetting;
    }

    class ResultSender extends Thread {

        private List<InetAddress> getListaVincitori() {
            Random randomGenerator = new Random();
            int cavalloVincente = randomGenerator.nextInt(1, 12);
            List<InetAddress> vincitori = new LinkedList<>();
            for (Bet bet : bets) {
                InetAddress ip = bet.getBetter();
                if (bet.getHorse() == cavalloVincente) {
                    vincitori.add(ip);
                }
            }
            return vincitori;
        }

        private<T> String formatListAsResponse(String header, List<T> parts) {
            StringBuilder sb = new StringBuilder();
            sb.append(header);
            sb.append(":\n");
            for (T part : parts) {
                sb.append("- ");
                sb.append(part);
                sb.append("\n");
            }
            return sb.toString();
        }

        @Override
        public void run() {
            print(Type.INFO, "Finished accepting bets", null, this, null);
            MulticastSocket ms = null;
            try {
                ms = new MulticastSocket();
                List<InetAddress> vincitori = getListaVincitori();
                String dString = vincitori.isEmpty() ? "Nessun vincitore" : formatListAsResponse("I vincitori sono", vincitori);
                InetAddress group = InetAddress.getByName(multicastGroup);
                while (true) {
                    TimeUnit.SECONDS.sleep(MULTICAST_DELAY); // race's duration
                    byte[] buf;
                    buf = dString.getBytes();
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, group, SERVER_MULTICAST_PORT);
                    ms.send(packet);
                    print(Type.INFO, "Broadcasting: " + dString, null, this, null);
                }
            } catch (IOException e) {
                print(Type.ERROR, "Multicast failed", null, this, e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                if (ms != null) ms.close();
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getRaceId());
            sb.append(": start time ");
            sb.append(getStartTime().getTime());
            return sb.toString();
        }
    }

}
