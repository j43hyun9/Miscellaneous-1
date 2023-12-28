package com.j43hyun9.miscellaneous1;

import com.j43hyun9.miscellaneous1.event.ClickEvent;
import com.j43hyun9.miscellaneous1.event.MenuEventListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
public final class Main extends JavaPlugin {


    /**
     * 해당 잡다한걸 관리하는 플러그인은 다음과 같은 것들의 라이브러리를 추가해줘야함.
     * Essentials , Citizens
     */
    Main main = this;
    int min=0, second =0;

    @Override
    public void onEnable() {
        this.getLogger().info("[AutoReboot] AutoReboot 1.0 v 활성화");
        getServer().getPluginManager().registerEvents(new ClickEvent(this), this);
        getServer().getPluginManager().registerEvents(new MenuEventListener(), this);

        // spawn Actionbar TimeTask 성능 문제로 OFF
        //new ActionbarTimeTask(this).playerActionbarTimeTask();


        rebootSet();


    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        return super.onCommand(sender, command, label, args);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerRebootScheduler(Timer timer, int min, int second , String unit) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(second == 0)
                    Bukkit.broadcastMessage("§a[AutoReboot] §f서버가 §e" + (60-min) + unit + "§f뒤 재시작됩니다.");
                if(min == 59 && second > 1)
                    Bukkit.broadcastMessage("§a[AutoReboot] §f서버가 §c" + (60-second) + unit + "§f뒤 재시작됩니다.");
                if(second==59) {
                    Bukkit.broadcastMessage("§a[AutoReboot] §f서버가 §c재시작됩니다.");
                    Bukkit.shutdown();
                }
            }
        };
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DATE)+1,
                0,
                min,
                second);
        // Run
        timer.schedule(task, calendar.getTime()); // task를 calender.getTime() 시간에 실행함.

    }

    private void rebootSet() {
        this.getLogger().info("[AutoReboot] AutoReboot 1.0 v 활성화");
        // 분 단위
        registerRebootScheduler(new Timer(), 30, 0,"분");
        registerRebootScheduler(new Timer(), 40, 0,"분");
        registerRebootScheduler(new Timer(), 50, 0,"분");
        registerRebootScheduler(new Timer(), 55, 0,"분");
        registerRebootScheduler(new Timer(), 58, 0,"분");
        registerRebootScheduler(new Timer(), 59, 0,"분");
        // 초 단위
        registerRebootScheduler(new Timer(), 59, 50,"초");
        registerRebootScheduler(new Timer(), 59, 55,"초");
        registerRebootScheduler(new Timer(), 59, 56,"초");
        registerRebootScheduler(new Timer(), 59, 57,"초");
        registerRebootScheduler(new Timer(), 59, 58,"초");
        registerRebootScheduler(new Timer(), 59, 59,"초");
    }
}
