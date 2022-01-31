package zlfn.fantastic

import org.bukkit.Location
import org.bukkit.World

class AreaChecker {
    companion object
    {
        val spawnArea = 5
        val safeArea = 100
        val dangerArea = 1000
        fun checkSpawnAreaBlock(loc: Location):Boolean
        {
            if(loc.world!!.environment==World.Environment.NORMAL)
                return loc.x<spawnArea&&loc.z<spawnArea&&loc.x>-(spawnArea+1)&&loc.z>-(spawnArea+1)
            else return false
        }
        fun checkSpawnArea(loc:Location):Boolean
        {
            if(loc.world!!.environment==World.Environment.NORMAL)
                return loc.x<spawnArea&&loc.z<spawnArea&&loc.x>-spawnArea&&loc.z>-spawnArea
            else return false
        }
        fun checkSafeAreaBlock(loc:Location):Boolean
        {
            if(loc.world!!.environment==World.Environment.NORMAL)
                return loc.x<safeArea&&loc.z<safeArea&&loc.x>-(safeArea+1)&&loc.z>-(safeArea+1)
            else return false
        }
        fun checkSafeArea(loc:Location):Boolean
        {
            if(loc.world!!.environment==World.Environment.NORMAL)
                return loc.x<safeArea&&loc.z<safeArea&&loc.x>-safeArea&&loc.z>-safeArea
            else return false
        }
        fun checkDangerAreaBlock(loc:Location):Boolean
        {
            if(loc.world!!.environment==World.Environment.NORMAL)
                return loc.x< dangerArea&&loc.z<dangerArea&&loc.x>=(dangerArea+1)&&loc.z>-(dangerArea+1)
            else return false
        }
        fun checkDangerArea(loc:Location):Boolean
        {
            if(loc.world!!.environment==World.Environment.NORMAL)
                return loc.x< dangerArea&&loc.z< dangerArea&&loc.x>-dangerArea&&loc.z>-dangerArea
            else return false
        }

    }
}