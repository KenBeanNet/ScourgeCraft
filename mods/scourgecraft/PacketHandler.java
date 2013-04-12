package mods.scourgecraft;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

public class PacketHandler implements IPacketHandler
{
    public void onPacketData(INetworkManager var1, Packet250CustomPayload var2, Player var3)
    {
        ByteArrayDataInput var4 = ByteStreams.newDataInput(var2.data);
        int var5 = var4.readInt();
        int var6 = var4.readInt();
        int var7 = var4.readInt();
        int var8 = var4.readInt();
        int var9 = var4.readInt();
        int var10 = var4.readInt();
        int var11 = var4.readInt();
    }
}
