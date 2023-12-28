package com.j43hyun9.miscellaneous1.event;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.j43hyun9.miscellaneous1.message.HeadList;
import com.j43hyun9.miscellaneous1.message.Message;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Skull;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.profile.PlayerTextures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

public class MenuEventListener implements Listener {

    Inventory inv;
    Plugin main;

    public MenuEventListener() {
        // 인벤토리 생성
        inv = Bukkit.createInventory(null, 27, "§f§l메인메뉴");

        // 인벤토리 아이템 집어넣기
        initializeItens();
        fillDisplayItem(Material.BLACK_STAINED_GLASS_PANE, " ");


    }
    @EventHandler
    public void onMenu(PlayerSwapHandItemsEvent e) {

        Player p = e.getPlayer();

        if(p.isSneaking()) {
            inv.setItem(13, getMyHead(p));
            openInventory(p);

            }

        }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        boolean on = false;
        if(!e.getInventory().equals(inv)) return;

        Player p = (Player) e.getWhoClicked();

        final ItemStack clickedItem = e.getCurrentItem();

        // 누른 아이템이 검은색 유리판인지 확인하기.
        if(clickedItem.getType().equals(Material.BLACK_STAINED_GLASS_PANE)) {
            on = true;
            e.setCancelled(true);
        }

        if(clickedItem.getItemMeta().getDisplayName().equals("§f§l상점메뉴")) {
            e.setCancelled(true);
            inv.close();
            Bukkit.dispatchCommand(p, "shop");
            //player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
            //player.sendMessage(Bukkit.getWorld("lind").getPlayers().toString());
        }
        else if(clickedItem.getItemMeta().getDisplayName().equals("§f§l도움말")) {
            p.sendMessage("");
            p.sendMessage("§d§l기본명령어");
            p.sendMessage("");
            p.sendMessage("§e/tpa <닉네임> §f- 상대방에게 텔레포트 요청을 보냅니다.");
            p.sendMessage("§e/sethome §f- 현재 자신이 서 있는 곳의 위치를 저장합니다.");
            p.sendMessage("§e/home §f- sethome을 통해 저장한 위치로 텔레포트합니다.");
            p.sendMessage("§e/back §f- 가장 최근 텔레포트 또는 죽은 지점으로 이동합니다.");
            p.sendMessage("");
            p.sendMessage("§e§l→ 또 다른 명령어들 살펴보기");
            p.sendMessage("§f- /도움말 지역보호");
            p.sendMessage("§f- /도움말 로그조사");
            p.sendMessage("");
        }
        else if(clickedItem.getItemMeta().getDisplayName().equals("§9§l디스코드")) {
            p.sendMessage("");
            p.sendMessage("§9§lhttps://discord.gg/ue2dtWJjtV");
            p.sendMessage("");

        }

        else if(clickedItem.getItemMeta().getDisplayName().equals("§6자주 묻는 질문 확인하기")) {
        }

        if(!(on)) { // 유리판 클릭 X
            e.setCancelled(true);
            inv.close();

        }

    }

    // 인벤토리 드래그? 취소
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if ( e.getInventory().equals(inv)) {
            Player p = (Player) e.getWhoClicked();
            p.sendMessage("Dragsetcan:1");
            e.setCancelled(true);
            p.sendMessage("Dragsetcan:2");

        }
    }
    public void initializeItens() {
        //inv.setItem(4 ,createGuiItem(Material.PLAYER_HEAD, "§f상점메뉴", Message.shop1));
        //inv.addItem(createGuiItem(Material.IRON_HELMET, "Example SHelmet", "§First lien of the lore", "§Second line of the lore"));

        inv.setItem(3, getCustomHead(HeadList.SHOP_URL, "§f§l상점메뉴", Message.SHOP));
        inv.setItem(4, getCustomHead(HeadList.USERSHOP_URL, "§6§l유저마켓", Message.USER_MARKET));
        inv.setItem(5, getCustomHead(HeadList.COMMAND_URL, "§f§l도움말", Message.COMMNAD));
        inv.setItem(12, getCustomHead(HeadList.DAILY_URL, "§6§l출석체크", Message.DAILY));
        inv.setItem(14, getCustomHead(HeadList.DISCORD_URL, "§9§l디스코드", Message.DISCORD));
        inv.setItem(21, getCustomHead(HeadList.VOTE_URL, "§a§l서버추천", Message.VOTE));
        inv.setItem(22, getCustomHead(HeadList.SETTING_URL, "§f§l개인설정", Message.SETTING));
        inv.setItem(23, getCustomHead(HeadList.QUESTION_URL, "§6자주 묻는 질문 확인하기", Message.QUESTION));
    }
    protected ItemStack getCustomHead(String url, String name, String... lore) {
        PlayerProfile profile = getProfile(url);
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        SkullMeta skull = (SkullMeta) head.getItemMeta();
        meta.setOwnerProfile(profile);
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        head.setItemMeta(meta);


        return head;
    }

    protected  ItemStack getMyHead(Player p) {

        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        meta.setOwnerProfile(p.getPlayerProfile());
        meta.setDisplayName("§6"+p.getName());

        item.setItemMeta(meta);

        return item;

    }

    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();


        // 아이템의 디스플레이명 지정
        meta.setDisplayName(name);

        // 아이템 로어 지정
        meta.setLore(Arrays.asList(lore));

        // 메타 = 아이템의 nbt 정보를 뜻함. 즉 item틀에 nbt정보를 덧씌움
        item.setItemMeta(meta);

        return item;
    }
    private static final UUID RANDOM_UUID = UUID.fromString("92864445-51c5-4c3b-9039-517c9927d1b4"); // We reuse the same "random" UUID all the time
    private static PlayerProfile getProfile(String url) {
        PlayerProfile profile = (PlayerProfile) Bukkit.createPlayerProfile(RANDOM_UUID); // Get a new player profile
        PlayerTextures textures = profile.getTextures();
        URL urlObject;
        try {
            urlObject = new URL(url); // The URL to the skin, for example: https://textures.minecraft.net/texture/18813764b2abc94ec3c3bc67b9147c21be850cdf996679703157f4555997ea63a
        } catch (MalformedURLException exception) {
            throw new RuntimeException("Invalid URL", exception);
        }
        textures.setSkin(urlObject); // Set the skin of the player profile to the URL
        profile.setTextures(textures); // Set the textures back to the profile
        return profile;
    }
    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inv);
    }

    public void fillDisplayItem(final Material material, final String name) {
        final ItemStack fillitem = new ItemStack(material, 1);
        final ItemMeta meta = fillitem.getItemMeta();
        meta.setDisplayName(name);
        fillitem.setItemMeta(meta);

        for(int i=0; i<inv.getSize(); i++) {
            if(inv.getItem(i) == null) {
                inv.setItem(i, fillitem);
            }

        }
    }
}



