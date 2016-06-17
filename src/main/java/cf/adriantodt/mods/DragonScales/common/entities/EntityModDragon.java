package cf.adriantodt.mods.DragonScales.common.entities;
//
//import net.minecraft.block.Block;
//import net.minecraft.block.StepSound;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityAgeable;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.SharedMonsterAttributes;
//import net.minecraft.entity.ai.EntityAIAttackOnCollide;
//import net.minecraft.entity.ai.EntityAIFollowOwner;
//import net.minecraft.entity.ai.EntityAIHurtByTarget;
//import net.minecraft.entity.ai.EntityAILeapAtTarget;
//import net.minecraft.entity.ai.EntityAILookIdle;
//import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
//import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
//import net.minecraft.entity.ai.EntityAISwimming;
//import net.minecraft.entity.ai.EntityAIWander;
//import net.minecraft.entity.ai.EntityAIWatchClosest;
//import net.minecraft.entity.ai.attributes.Attribute;
//import net.minecraft.entity.ai.attributes.AttributeInstance;
//import net.minecraft.entity.ai.attributes.RangedAttribute;
//import net.minecraft.entity.passive.EntityTameable;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.entity.projectile.EntityArrow;
//import net.minecraft.inventory.AnimalChest;
//import net.minecraft.inventory.InventoryBasic;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.pathfinding.PathEntity;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.MathHelper;
//import net.minecraft.world.World;
//import Jakester12.DragonScales.KeyBindings;
//import Jakester12.DragonScales.MainClass;
//import cpw.mods.fml.common.FMLCommonHandler;
//import cpw.mods.fml.common.network.Player;
//import cpw.mods.fml.relauncher.Side;
//import cpw.mods.fml.relauncher.SideOnly;
//import hyghlander.mods.DragonScales.common.events.PlayerTickHandler;
//
//public class EntityModDragon extends EntityTameable
//{
//    private static final Attribute horseJumpStrength = (new RangedAttribute("horse.jumpStrength", 0.7D, 0.0D, 2.0D)).func_111117_a("Jump Strength").setShouldWatch(true);
//
//    private static final int[] armorValues = new int[] {0, 5, 7, 11};
//
//    
//	public static boolean onGround2;
//
//	public static Player playername;
//
//    public int field_110278_bp;
//    public int field_110279_bq;
//    protected boolean horseJumping;
//    private AnimalChest horseChest;
//    private boolean hasReproduced;
//    private Entity field_110191_bu;
//    /**
//     * "The higher this value, the more likely the horse is to be tamed next time a player rides it."
//     */
//    protected int temper;
//    protected float jumpPower;
//    private boolean field_110294_bI;
//    private float headLean;
//    private float prevHeadLean;
//    private float rearingAmount;
//    private float prevRearingAmount;
//    private float mouthOpenness;
//    private float prevMouthOpenness;
//    private int field_110285_bP;
//    private String field_110286_bQ;
//    private String[] field_110280_bR = new String[3];
//    private int angerLevel;
//    
//    public EntityModDragon(World par1World)
//    {
//        super(par1World);
//        this.setSize(2F, 5.5F);
//            this.isImmuneToFire = true;
//        this.getNavigator().setAvoidsWater(true);
//        this.tasks.addTask(0, new EntityAISwimming(this));
//        this.tasks.addTask(2, new EntityAIWander(this, 0.7D));
//        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
//        this.tasks.addTask(4, new EntityAILookIdle(this));
//        	 this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 1.0D, true));
//        	   this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
//               this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
//               this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
//               this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
//               this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
//               this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
//              this.setTamed(false);
//    }
// 
//    protected void entityInit()
//    {
//        super.entityInit();
//        this.dataWatcher.addObject(19, Byte.valueOf((byte)0));
//        this.dataWatcher.addObject(20, Integer.valueOf(0));
//        this.dataWatcher.addObject(21, String.valueOf(""));
//        this.dataWatcher.addObject(22, Integer.valueOf(0));
//    }
//    
//    /**
//     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
//     * (Animals, Spiders at day, peaceful PigZombies).
//     */
//    
//    public void setAttackTarget(EntityLivingBase par1EntityLivingBase)
//    {
//        super.setAttackTarget(par1EntityLivingBase);
//
//        if (par1EntityLivingBase == null)
//        {
//            this.setAngry(false);
//        }
//        else if (!this.isTamed())
//        {
//            this.setAngry(true);
//        }
//    }
//
//
//    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
//    {
//        super.writeEntityToNBT(par1NBTTagCompound);
//        par1NBTTagCompound.setBoolean("Angry", this.isAngry());
//    }
//
//
//	/**
//     * (abstract) Protected helper method to read subclass entity data from NBT.
//     */
//    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
//    {
//        super.readEntityFromNBT(par1NBTTagCompound);
//        this.setAngry(par1NBTTagCompound.getBoolean("Angry"));
//
//    }
//
//    public boolean isAngry()
//    {
//        return (this.dataWatcher.getWatchableObjectByte(16) & 2) != 0;
//    }
//
//    /**
//     * Sets whether this wolf is angry or not.
//     */
//    public void setAngry(boolean par1)
//    {
//        byte b0 = this.dataWatcher.getWatchableObjectByte(16);
//
//        if (par1)
//        {
//            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 2)));
//        }
//        else
//        {
//            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -3)));
//        }
//    }
//
//
//    /**
//     * Causes this PigZombie to become angry at the supplied Entity (which will be a player).
//     */
//    private void becomeAngryAt(Entity par1Entity)
//    {
//        this.entityToAttack = par1Entity;
//        this.angerLevel = 20;
//    }
//    
//    public void setHorseType(int par1)
//    {
//        this.dataWatcher.updateObject(19, Byte.valueOf((byte)par1));
//        this.func_110230_cF();
//    }
//
//    /**
//     * returns the horse type
//     */
//    public int getHorseType()
//    {
//        return this.dataWatcher.getWatchableObjectByte(19);
//    }
//
//    public void setHorseVariant(int par1)
//    {
//        this.dataWatcher.updateObject(20, Integer.valueOf(par1));
//        this.func_110230_cF();
//    }
//
//    public int getHorseVariant()
//    {
//        return this.dataWatcher.getWatchableObjectInt(20);
//    }
//    
//    public boolean isAdultHorse()
//    {
//        return !this.isChild();
//    }
//
//    public boolean func_110253_bW()
//    {
//        return this.isAdultHorse();
//    }
//
//    public String getOwnerName()
//    {
//        return this.dataWatcher.getWatchableObjectString(21);
//    }
//
//    public void setOwnerName(String par1Str)
//    {
//        this.dataWatcher.updateObject(21, par1Str);
//    }
//
//
//
//    public boolean isHorseJumping()
//    {
//        return this.horseJumping;
//    }
//
//    public void setHorseJumping(boolean par1)
//    {
//        this.horseJumping = par1;
//    }
//
//    public boolean allowLeashing()
//    {
//        return !this.func_110256_cu() && super.allowLeashing();
//    }
//
//
//    public int func_110241_cb()
//    {
//        return this.dataWatcher.getWatchableObjectInt(22);
//    }
//
//    /**
//     * 0 = iron, 1 = gold, 2 = diamond
//     */
//    public int getHorseArmorIndex(ItemStack par1ItemStack)
//    {
//        return par1ItemStack == null ? 0 : (par1ItemStack.itemID == Item.horseArmorIron.itemID ? 1 : (par1ItemStack.itemID == Item.horseArmorGold.itemID ? 2 : (par1ItemStack.itemID == Item.horseArmorDiamond.itemID ? 3 : 0)));
//    }
//
// 
//    public boolean getHasReproduced()
//    {
//        return this.hasReproduced;
//    }
//
//    public void func_110236_r(int par1)
//    {
//        this.dataWatcher.updateObject(22, Integer.valueOf(par1));
//        this.func_110230_cF();
//    }
//
//    public void setHasReproduced(boolean par1)
//    {
//        this.hasReproduced = par1;
//    }
//
//    public int getTemper()
//    {
//        return this.temper;
//    }
//
//    public void setTemper(int par1)
//    {
//        this.temper = par1;
//    }
//
//    public int increaseTemper(int par1)
//    {
//        int j = MathHelper.clamp_int(this.getTemper() + par1, 0, this.getMaxTemper());
//        this.setTemper(j);
//        return j;
//    }
//
//    /**
//     * Called when the entity is attacked.
//     */
//  
//
//    /**
//     * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
//     */
//    public int getTotalArmorValue()
//    {
//        return armorValues[this.func_110241_cb()];
//    }
//
//    /**
//     * Returns true if this entity should push and be pushed by other entities when colliding.
//     */
//    public boolean canBePushed()
//    {
//        return this.riddenByEntity == null;
//    }
//
//    public boolean prepareChunkForSpawn()
//    {
//        int i = MathHelper.floor_double(this.posX);
//        int j = MathHelper.floor_double(this.posZ);
//        this.worldObj.getBiomeGenForCoords(i, j);
//        return true;
//    }
//
//
//    private void func_110266_cB()
//    {
//        
//        this.worldObj.playSoundAtEntity(this, "eating", 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
//    }
//
//    /**
//     * Called when the mob is falling. Calculates and applies fall damage.
//     */
//    protected void fall(float par1)
//    {
//     
//        
//    }
//
//  
//
//    /**
//     * Called by InventoryBasic.onInventoryChanged() on a array that is never filled.
//     */
//    public void onInventoryChanged(InventoryBasic par1InventoryBasic)
//    {
//        int i = this.func_110241_cb();
//        boolean flag = this.isHorseSaddled();
//
//        if (this.ticksExisted > 20)
//        {
//            if (i == 0 && i != this.func_110241_cb())
//            {
//                this.playSound("mob.horse.armor", 0.5F, 1.0F);
//            }
//
//            if (!flag && this.isHorseSaddled())
//            {
//                this.playSound("mob.horse.leather", 0.5F, 1.0F);
//            }
//        }
//    }
//
//    /**
//     * Checks if the entity's current position is a valid location to spawn this entity.
//     */
//    public boolean getCanSpawnHere()
//    {
//        this.prepareChunkForSpawn();
//        return super.getCanSpawnHere();
//    }
//
//    
//    public double getHorseJumpStrength()
//    {
//        return this.getEntityAttribute(horseJumpStrength).getAttributeValue();
//    }
//
//    /**
//     * Returns the sound this mob makes on death.
//     */
//    protected String getDeathSound()
//    {
//  
//        int i = this.getHorseType();
//        return i == 3 ? "mob.horse.zombie.death" : (i == 4 ? "mob.horse.skeleton.death" : (i != 1 && i != 2 ? "mob.horse.death" : "mob.horse.donkey.death"));
//    }
//
//    /**
//     * Returns the item ID for the item the mob drops on death.
//     */
//    protected int getDropItemId()
//    {
//        boolean flag = this.rand.nextInt(4) == 0;
//        int i = this.getHorseType();
//        return i == 4 ? Item.bone.itemID : (i == 3 ? (flag ? 0 : Item.rottenFlesh.itemID) : Item.leather.itemID);
//    }
//
//    /**
//     * Returns the sound this mob makes when it is hurt.
//     */
//    protected String getHurtSound()
//    {
//    
//
//        if (this.rand.nextInt(3) == 0)
//        {
//         
//        }
//
//        int i = this.getHorseType();
//        return "mob.enderdragon.growl";
//    }
//
//    public boolean isHorseSaddled()
//    {
//        return true;
//    }
//
//    /**
//     * Returns the sound this mob makes while it's alive.
//     */
//    protected String getLivingSound()
//    {
//      
//
//        if (this.rand.nextInt(10) == 0 && !this.isMovementBlocked())
//        {
//           
//        }
//
//        int i = this.getHorseType();
//        return "mob.enderdragon.idle";
//    }
//
//    protected String getAngrySoundName()
//    {
//      
//      
//        int i = this.getHorseType();
//        return i != 3 && i != 4 ? (i != 1 && i != 2 ? "mob.horse.angry" : "mob.horse.donkey.angry") : null;
//    }
//
//    /**
//     * Plays step sound at given x, y, z for the entity
//     */
//    protected void playStepSound(int par1, int par2, int par3, int par4)
//    {
//        StepSound stepsound = Block.blocksList[par4].stepSound;
//
//        if (this.worldObj.getBlockId(par1, par2 + 1, par3) == Block.snow.blockID)
//        {
//            stepsound = Block.snow.stepSound;
//        }
//
//        if (!Block.blocksList[par4].blockMaterial.isLiquid())
//        {
//            int i1 = this.getHorseType();
//
//            if (this.riddenByEntity != null && i1 != 1 && i1 != 2)
//            {
//                ++this.field_110285_bP;
//
//                if (this.field_110285_bP > 5 && this.field_110285_bP % 3 == 0)
//                {
//                    this.playSound("mob.horse.gallop", stepsound.getVolume() * 0.15F, stepsound.getPitch());
//
//                    if (i1 == 0 && this.rand.nextInt(10) == 0)
//                    {
//                        this.playSound("mob.horse.breathe", stepsound.getVolume() * 0.6F, stepsound.getPitch());
//                    }
//                }
//                else if (this.field_110285_bP <= 5)
//                {
//                    this.playSound("mob.horse.wood", stepsound.getVolume() * 0.15F, stepsound.getPitch());
//                }
//            }
//            else if (stepsound == Block.soundWoodFootstep)
//            {
//                this.playSound("mob.horse.soft", stepsound.getVolume() * 0.15F, stepsound.getPitch());
//            }
//            else
//            {
//                this.playSound("mob.horse.wood", stepsound.getVolume() * 0.15F, stepsound.getPitch());
//            }
//        }
//    }
//
//    protected void applyEntityAttributes()
//    {
//        super.applyEntityAttributes();
//        this.getAttributeMap().func_111150_b(horseJumpStrength);
//        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(100.0D);
//        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.22499999403953552D);
//    }
//
//    /**
//     * Will return how many at most can spawn in a chunk at once.
//     */
//    public int getMaxSpawnedInChunk()
//    {
//        return 2;
//    }
//
//    public int getMaxTemper()
//    {
//        return 100;
//    }
//
//    /**
//     * Returns the volume for the sounds this mob makes.
//     */
//    protected float getSoundVolume()
//    {
//        return 0.8F;
//    }
//
//    /**
//     * Get number of ticks, at least during which the living entity will be silent.
//     */
//    public int getTalkInterval()
//    {
//        return 400;
//    }
//
//    @SideOnly(Side.CLIENT)
//    public boolean func_110239_cn()
//    {
//        return this.getHorseType() == 0 || this.func_110241_cb() > 0;
//    }
//
//    private void func_110230_cF()
//    {
//        this.field_110286_bQ = null;
//    }
//    
//    
//
//
//
//    /**
//     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
//     */
//    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
//    {
//        if (this.isEntityInvulnerable())
//        {
//            return false;
//        }
//        else
//        {
//            Entity entity = par1DamageSource.getEntity();
//            this.aiSit.setSitting(false);
//
//            if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))
//            {
//                par2 = (par2 + 1.0F) / 2.0F;
//            }
//
//            return super.attackEntityFrom(par1DamageSource, par2);
//        }
//    }
//
//    public boolean attackEntityAsMob(Entity par1Entity)
//    {
//        int i = this.isTamed() ? 4 : 2;
//        return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float)i);
//    }
//
//    public void setTamed(boolean par1)
//    {
//        super.setTamed(par1);
//
//        if (par1)
//        {
//            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(100.0D);
//        }
//        else
//        {
//            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(100.0D);
//        }
//    }
//
//    /**
//     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
//     */
//    public boolean interact(EntityPlayer par1EntityPlayer)
//    {
//        ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
//
//        if (this.isTamed())
//        {
//
//            if (par1EntityPlayer.getCommandSenderName().equalsIgnoreCase(this.getOwnerName()) && !this.worldObj.isRemote && !this.isBreedingItem(itemstack))
//            {
//                this.aiSit.setSitting(!this.isSitting());
//                this.isJumping = false;
//                this.setPathToEntity((PathEntity)null);
//                this.setTarget((Entity)null);
//                this.setAttackTarget((EntityLivingBase)null);
//            }
//        }
//        if (itemstack != null && itemstack.itemID == MainClass.dragonsaddle.itemID == true && !this.isAngry())
//        {
//          
//
//            if (itemstack.stackSize <= 0)
//            {
//                par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
//            }
//
//            if (!this.worldObj.isRemote)
//            {
//                if (this.rand.nextInt(3) == 0)
//                {
//                    this.setTamed(false);
//                    this.setPathToEntity((PathEntity)null);
//                    this.setAttackTarget((EntityLivingBase)null);
//                    this.aiSit.setSitting(true);
//                    this.setHealth(100.0F);
//                    this.setOwner(par1EntityPlayer.getCommandSenderName());
//                    this.playTameEffect(true);
//                    this.worldObj.setEntityState(this, (byte)7);
//                }
//                else
//                {
//                    this.playTameEffect(false);
//                    this.worldObj.setEntityState(this, (byte)6);
//                }
//            }
//        ItemStack itemstack1 = par1EntityPlayer.inventory.getCurrentItem();
//
//        if (itemstack1 != null && itemstack1.itemID == Item.monsterPlacer.itemID)
//        {
//            return super.interact(par1EntityPlayer);
//        }
//        else if (!this.isTamed() && this.func_110256_cu())
//        {
//            return false;
//        }
//       
//        else if (this.func_110253_bW() && this.riddenByEntity != null)
//        {
//            return super.interact(par1EntityPlayer);
//        }
//        else
//        {
//          
//
//            if (this.func_110253_bW() && this.riddenByEntity == null)
//            {
//                if (itemstack1 != null && itemstack1.func_111282_a(par1EntityPlayer, this))
//                {
//                    return true;
//                }
//                else
//                {
//                    this.func_110237_h(par1EntityPlayer);
//                    return true;
//                }
//            }
//            else
//            {
//                return super.interact(par1EntityPlayer);
//            }
//        }
//        }
//		return field_110294_bI;
//    }
//
//    private void func_110237_h(EntityPlayer par1EntityPlayer)
//    {
//        par1EntityPlayer.rotationYaw = this.rotationYaw;
//        par1EntityPlayer.rotationPitch = this.rotationPitch;
//        this.setEatingHaystack(false);
//
//        if (!this.worldObj.isRemote)
//        {
//            par1EntityPlayer.mountEntity(this);
//        }
//    }
//
//    public boolean func_110259_cr()
//    {
//        return this.getHorseType() == 0;
//    }
//
//    public boolean func_110229_cs()
//    {
//        int i = this.getHorseType();
//        return i == 2 || i == 1;
//    }
//
//    /**
//     * Dead and sleeping entities cannot move
//     */
//    protected boolean isMovementBlocked()
//    {
//        return this.riddenByEntity != null;
//    }
//
//    public boolean func_110256_cu()
//    {
//        int i = this.getHorseType();
//        return i == 3 || i == 4;
//    }
//
//    public boolean func_110222_cv()
//    {
//        return this.func_110256_cu() || this.getHorseType() == 2;
//    }
//
//    /**
//     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
//     * the animal type)
//     */
//    public boolean isBreedingItem(ItemStack par1ItemStack)
//    {
//        return false;
//    }
//
//    private void func_110210_cH()
//    {
//        this.field_110278_bp = 1;
//    }
//
//    /**
//     * Called when the mob's health reaches 0.
//     */
//    public void onDeath(DamageSource par1DamageSource)
//    {
//        super.onDeath(par1DamageSource);
//
//        if (!this.worldObj.isRemote)
//        {
//            this.dropChestItems();
//        }
//    }
//
//    
//    
//    /**
//     * Called to update the entity's position/logic.
//     */
//    public void onUpdate()
//    {
//        if (this.field_110191_bu != this.entityToAttack && !this.worldObj.isRemote)
//        {
//            AttributeInstance attributeinstance = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
//
//            
//        }
//
//        this.field_110191_bu = this.entityToAttack;
//
//
//        super.onUpdate();
//    }
//
//    private boolean func_110200_cJ()
//    {
//        return this.riddenByEntity == null && this.ridingEntity == null && this.isTamed() && this.isAdultHorse() && !this.func_110222_cv() && this.getHealth() >= this.getMaxHealth();
//    }
//
//    public void setEatingHaystack(boolean par1)
//    {
//        this.setEating(par1);
//    }
//
//
//    public void makeHorseRearWithSound()
//    {
//
//        String s = this.getAngrySoundName();
//
//        if (s != null)
//        {
//            this.playSound(s, this.getSoundVolume(), this.getSoundPitch());
//        }
//    }
//
//    public void dropChestItems()
//    {
//        this.dropItemsInChest(this, this.horseChest);
//    }
//
//    private void dropItemsInChest(Entity par1Entity, AnimalChest par2AnimalChest)
//    {
//        if (par2AnimalChest != null && !this.worldObj.isRemote)
//        {
//            for (int i = 0; i < par2AnimalChest.getSizeInventory(); ++i)
//            {
//                ItemStack itemstack = par2AnimalChest.getStackInSlot(i);
//
//                if (itemstack != null)
//                {
//                    this.entityDropItem(itemstack, 0.0F);
//                }
//            }
//        }
//    }
//
//    public boolean setTamedBy(EntityPlayer par1EntityPlayer)
//    {
//        this.setOwnerName(par1EntityPlayer.getCommandSenderName());
//        this.setTamed(true);
//        return true;
//    }
//    /**
//     * Moves the entity based on the specified heading.  Args: 
//, forward
//     */
//    public void moveEntityWithHeading(float par1, float par2)
//
//    {
//    	
//    	float flyheight = 0;
//    	if(this.onGround == true){
//    		flyheight = 0;
//    	}
//    	if(PlayerTickHandler.DragonKeyJump == true && flyheight < 2){
//    		flyheight += 0.1F;
//    	}
//    	if (this.motionY <-0.1 && PlayerTickHandler.DragonKeyJump == false && this.riddenByEntity != null){
//    		this.motionY = -0.2F;
//    	}
//    	
//    	if (this.onGround == true){
//    		this.onGround2 = true;
//    	}else if( this.onGround == false){
//    		this.onGround2 = false;
//    	}
//    	
//        if (this.riddenByEntity != null)
//        {
//            this.prevRotationYaw = this.rotationYaw = this.riddenByEntity.rotationYaw;
//            this.rotationPitch = this.riddenByEntity.rotationPitch * 0.5F;
//            this.setRotation(this.rotationYaw, this.rotationPitch);
//            this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
//            par1 = ((EntityLivingBase)this.riddenByEntity).moveStrafing * 0.5F;
//            par2 = ((EntityLivingBase)this.riddenByEntity).moveForward;
//            
//          this.motionY += flyheight;
//            
//           
//            this.stepHeight = 1.0F;
//            this.jumpMovementFactor = this.getAIMoveSpeed() * 1F;
//
//            if (!this.worldObj.isRemote)
//            {
//                this.setAIMoveSpeed((float)this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
//                super.moveEntityWithHeading(par1, par2);
//            }
//
//            if (this.onGround)
//            {
//                this.jumpPower = 0.0F;
//                this.setHorseJumping(false);
//            }
//
//            this.prevLimbSwingAmount = this.limbSwingAmount;
//            double d0 = this.posX - this.prevPosX;
//            double d1 = this.posZ - this.prevPosZ;
//            float f4 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;
//
//            if (f4 > 1.0F)
//            {
//                f4 = 1.0F;
//            }
//
//            this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
//            this.limbSwing += this.limbSwingAmount;
//        }
//        else
//        {
//            this.stepHeight = 0.5F;
//            this.jumpMovementFactor = 0.02F;
//            super.moveEntityWithHeading(par1, par2);
//        }
//    }
//
//    /**
//     * (abstract) Protected helper method to write subclass entity data to NBT.
//     */
//   
//    /**
//     * Returns true if the newer Entity AI code should be run
//     */
//    protected boolean isAIEnabled()
//    {
//        return true;
//    }
//
//    public void setJumpPower(int par1)
//    {
//        if (this.isHorseSaddled())
//        {
//            if (par1 < 0)
//            {
//                par1 = 0;
//            }
//            else
//            {
//                this.field_110294_bI = true;
//          
//            }
//
//            if (par1 >= 90)
//            {
//                this.jumpPower = 1.0F;
//            }
//            else
//            {
//                this.jumpPower = 0.4F + 0.4F * (float)par1 / 90.0F;
//            }
//        }
//    }
//
//    @SideOnly(Side.CLIENT)
//
//    /**
//     * "Spawns particles for the horse entity. par1 tells whether to spawn hearts. If it is false, it spawns smoke."
//     */
//    protected void spawnHorseParticles(boolean par1)
//    {
//        String s = par1 ? "heart" : "smoke";
//
//        for (int i = 0; i < 7; ++i)
//        {
//            double d0 = this.rand.nextGaussian() * 0.02D;
//            double d1 = this.rand.nextGaussian() * 0.02D;
//            double d2 = this.rand.nextGaussian() * 0.02D;
//            this.worldObj.spawnParticle(s, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
//        }
//    }
//
//    @SideOnly(Side.CLIENT)
//    public void handleHealthUpdate(byte par1)
//    {
//        if (par1 == 7)
//        {
//            this.spawnHorseParticles(true);
//        }
//        else if (par1 == 6)
//        {
//            this.spawnHorseParticles(false);
//        }
//        else
//        {
//            super.handleHealthUpdate(par1);
//        }
//    }
//
//    public void updateRiderPosition()
//    {
//        super.updateRiderPosition();
//
//        if (this.prevRearingAmount > 0.0F)
//        {
//            float f = MathHelper.sin(this.renderYawOffset * (float)Math.PI / 180.0F);
//            float f1 = MathHelper.cos(this.renderYawOffset * (float)Math.PI / 180.0F);
//            float f2 = 0.7F * this.prevRearingAmount;
//            float f3 = 0.15F * this.prevRearingAmount;
//            this.riddenByEntity.setPosition(this.posX + (double)(f2 * f), this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset() + (double)f3, this.posZ - (double)(f2 * f1));
//
//            if (this.riddenByEntity instanceof EntityLivingBase)
//            {
//                ((EntityLivingBase)this.riddenByEntity).renderYawOffset = this.renderYawOffset;
//            }
//        }
//    }
//
//    private float func_110267_cL()
//    {
//        return 15.0F + (float)this.rand.nextInt(8) + (float)this.rand.nextInt(9);
//    }
//
//    private double func_110245_cM()
//    {
//        return 0.4000000059604645D + this.rand.nextDouble() * 0.2D + this.rand.nextDouble() * 0.2D + this.rand.nextDouble() * 0.2D;
//    }
//
//    private double func_110203_cN()
//    {
//        return (0.44999998807907104D + this.rand.nextDouble() * 0.3D + this.rand.nextDouble() * 0.3D + this.rand.nextDouble() * 0.3D) * 0.25D;
//    }
//
//    public static boolean func_110211_v(int par0)
//    {
//        return par0 == Item.horseArmorIron.itemID || par0 == Item.horseArmorGold.itemID || par0 == Item.horseArmorDiamond.itemID;
//    }
//
//    /**
//     * returns true if this entity is by a ladder, false otherwise
//     */
//    public boolean isOnLadder()
//    {
//        return false;
//    }
//
//	@Override
//	public EntityAgeable createChild(EntityAgeable entityageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//}
//