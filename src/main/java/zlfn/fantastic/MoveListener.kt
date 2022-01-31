package zlfn.fantastic

import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.*
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityExplodeEvent
import org.bukkit.event.entity.EntityRegainHealthEvent
import zlfn.fantastic.AreaChecker.Companion.checkDangerArea
import zlfn.fantastic.AreaChecker.Companion.checkSafeArea
import zlfn.fantastic.AreaChecker.Companion.checkSafeAreaBlock
import zlfn.fantastic.AreaChecker.Companion.checkSpawnArea
import zlfn.fantastic.AreaChecker.Companion.checkSpawnAreaBlock


class MoveListener : Listener{
    @EventHandler
    fun OnPlayerMove(event: PlayerMoveEvent) {
        val from = event.from!!
        val to = event.to!!
        if (checkSpawnArea(from)) {
            if (!checkSpawnArea(to))
                event.player.sendMessage("스폰구역을 벗어났습니다.")
        }
        if (checkSafeArea(from) && !checkSpawnArea(from)) {
            if (checkSpawnArea(to))
                event.player.sendMessage("스폰구역에 들어왔습니다.")
            if (!checkSafeArea(to))
                event.player.sendMessage("안전구역을 벗어났습니다.")
        }
        if (checkDangerArea(from)&&!checkSafeArea(from)) {
            if (checkSafeArea(to))
                event.player.sendMessage("안전구역에 들어왔습니다.")
            if (!checkDangerArea(to))
                event.player.sendMessage("무법지대에 들어왔습니다.")
        }
        if (!checkDangerArea(from))
        {
            if(checkDangerArea(to))
                event.player.sendMessage("무법지대를 벗어났습니다.")
        }
    }

    @EventHandler
    fun OnBlockBreak(event: BlockBreakEvent)
    {
        val loc = event.block.location!!
        if(checkSpawnAreaBlock(loc)&&!(event.player.gameMode== GameMode.CREATIVE))
        {
            event.isCancelled=true
        }
    }

    @EventHandler
    fun OnBlockPlace(event: BlockPlaceEvent)
    {
        var loc = event.block.location!!
        if(checkSpawnAreaBlock(loc)&&!(event.player.gameMode==GameMode.CREATIVE))
        {
            event.isCancelled=true
        }
    }

    @EventHandler
    fun OnBlockGrow(event: BlockGrowEvent) {
        var loc = event.block.location!!
        if (checkSafeAreaBlock(loc))
        {
            event.isCancelled=true
        }
    }

    @EventHandler
    fun OnCreatureSpawn(event: CreatureSpawnEvent)
    {
        if(checkSafeArea(event.entity.location))
        when(event.spawnReason)
        {
            SpawnReason.BREEDING,
                SpawnReason.BUILD_IRONGOLEM,
                SpawnReason.BUILD_SNOWMAN,
                SpawnReason.BUILD_WITHER,
                SpawnReason.EGG,
                SpawnReason.ENDER_PEARL,
                SpawnReason.INFECTION,
                SpawnReason.JOCKEY,
                SpawnReason.NATURAL,
                SpawnReason.NETHER_PORTAL,
                SpawnReason.PATROL,
                SpawnReason.RAID,
                SpawnReason.REINFORCEMENTS,
                SpawnReason.SILVERFISH_BLOCK,
                SpawnReason.SPAWNER,
                SpawnReason.SPELL,
                SpawnReason.TRAP,
                SpawnReason.VILLAGE_DEFENSE,
                SpawnReason.VILLAGE_INVASION ->
                event.isCancelled=true
        }
    }

    @EventHandler
    fun OnEntityExplode(event: EntityExplodeEvent)
    {
        val loc = event.entity.location
        if(checkSafeArea(loc))
            event.isCancelled=true
    }

    @EventHandler
    fun OnBlockMove(event: BlockFromToEvent)
    {
        var loc = event.toBlock.location
        if(checkSafeArea(loc)&&event.block.type==Material.LAVA)
            event.isCancelled=true
    }

    @EventHandler
    fun OnBlockIgnite(event:BlockIgniteEvent)
    {
        var loc = event.block.location
        if(checkSafeAreaBlock(loc))
            event.isCancelled=true
    }

    @EventHandler
    fun OnBlockBurn(event:BlockBurnEvent)
    {
        var loc = event.block.location
        if(checkSafeAreaBlock(loc))
            event.isCancelled=true
    }

    @EventHandler
    fun OnPlayerFight(event: EntityDamageByEntityEvent)
    {
        var entity = event.entity
        var damager = event.damager
        if(checkSafeArea(entity.location)&&damager.type==EntityType.PLAYER)
            event.isCancelled=true
    }

    @EventHandler
    fun OnHealing(event: EntityRegainHealthEvent)
    {
        if(!checkDangerArea(event.entity.location)&&event.regainReason==EntityRegainHealthEvent.RegainReason.SATIATED)
            event.isCancelled=true
    }
}