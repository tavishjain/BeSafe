package ytv.appathon.appathon_igdtu;

import java.util.Random;

public class SafteyTipsFetch {

    private static String[][] safetyTips = new String[13][2];
    static int generatedRandomNum = -1;

    public static void setAllTips(){

        safetyTips[0][0] = "Be Aware of Your Surroundings";
        safetyTips[0][1] = "Typically known as “situational awareness”, make it a habit to know what’s going on around you. When you walk into a room, look to your left and right to see who is lingering at the door, find your exits, look to see who is there. When you walk out of a door, immediately look to your left and right to see if anyone is lingering, look behind you often to make sure someone’s not following you. You don’t have to be paranoid..you just need to be aware.";

        safetyTips[1][0] = "Get Off Your Phone  ";
        safetyTips[1][1] = "Is that phone call or text to your best friend about the Walmart chick you saw wearing nude colored leggings REALLY as important as your safety? That call/text can wait until you are in your car safely (with the doors locked). Not only are you able to be aware of your surroundings by any human threat, but you can pay attention to the traffic around you and not get run over. If you’ve got the kids in tow as well, you aren’t giving them your undivided attention in a potentially dangerous situation.";

        safetyTips[2][0] = "Don’t Leave Your Purse Unattended";
        safetyTips[2][1] = "Before you glaze over at this, hear me out. Not only are you going to lose your cash and your credit cards if someone grabs your purse, but your personal safety is at risk because they now have your home address. This extends the danger time to well past the moment he or she walked by your shopping cart or restaurant chair and nicked your bag when you weren’t paying attention to the next time you’re away at a soccer game, and they break in while you’re gone. Or…while you’re there. And if not them, someone they know looking for an easy mark.";

        safetyTips[3][0] = "Lock the Door Behind You";
        safetyTips[3][1] = "Make it an absolute rule of thumb that when you get into your car, lock the door immediately (if nothing else, just use the remote you’ve already got in your hand to lock up, too). When you come in from outside, lock the door immediately.  When you go into a bathroom stall – lock first THEN find a place to put your bag. Too often bad guys don’t get you when you’re going into the building or car but will wait until you think you’re safe and relax your guard, and then open that door and you’re stuck.";

        safetyTips[4][0] = "Speak Up";
        safetyTips[4][1] = "Do not be afraid to speak to someone to ask them to not come closer if approaching you in a parking lot. Do not be afraid to yell for help if they don’t stop. Do not be afraid to speak loudly to someone so that you can get the attention of others nearby. Do not be afraid to let your wishes be known that you do not want to be harmed. Might you be embarrassed if it turns out that you misunderstood the situation? What’s a few minutes embarrassment when you read a situation incorrectly and ask for help compared to a situation where you stood and froze, too scared to say something because you didn’t want to embarrass yourself and were harmed.";

        safetyTips[5][0] = "Park Safely";
        safetyTips[5][1] = "When parking, choose spots that are well-lit, amongst other cars, and as close to the main entrances of a building as you can (not so you can be lazy so that you spend less time in the car park as possible). Lock your car quickly, but keep your keys handy in case you have to get back in. Be aware of someone loitering near your car (do not approach), and always check in and around your car before entering. When loading your trunk, do so as quickly as you can and always keep aware on your surroundings of someone approaching";

        safetyTips[6][0] = "Answer the Door";
        safetyTips[6][1] = "Typically, we’ve been told, “If you’re home alone and someone knocks, don’t answer – they’ll go away.” Recent events, though, are showing that more and more, burglars are doing home invasions assuming that if no one answers, it’s safe to go in. Locally, we’ve been told by our police department to answer with a call for who it is, and let them know you’re on the phone or won’t be answering the door or other ways to let the person know you aren’t going to be opening the door. Never actually answer the door even for a service person you are expecting until you’ve been able to confirm that they are actually who they say they are.";

        safetyTips[7][0] = "Trust Your Instincts";
        safetyTips[7][1] = "Trust your gut. If you feel something weird about a situation, get out of it. If you have a sense of dread about walking into a dark area, don’t – find some light. If a person is giving you cause to be concerned, excuse yourself from the conversation or cross the street to get away from them.  I’m not talking about paranoia, I’m talking about that instinct that we tend to push aside – the hair raising on the back of your neck, goosebumps on your arms…all telling you to flee!";

        safetyTips[8][0] = "Learn how to Use Your Gun";
        safetyTips[8][1] = "Do not go out and buy a pretty little gun, put it in a purse and never touch it again. When the time comes to use it, will you be able to draw it safely? Know how to get it off safety? Know how to aim and shoot? Know how to take care of a jam? Know how to reload? Get out to a range and learn to shoot your weapon. Learn how to handle it safely. Practice drawing and dry firing (if your gun works well, or get snap caps to use). Practice with your husband (or friend) acting as an attacker so that you can practice how you’ll react (just don’t get mad at him if he’s good at it!) Practice safely, but practice. Knowing how to use your gun effectively and safely will allow you to have another layer of defense between you and an attacker. Too many times, people who don’t know how to handle their weapons end up with said weapon turned against them. Don’t be that woman.";

        safetyTips[9][0] = "Don’t Make Yourself a Target";
        safetyTips[9][1] = "Wearing flashy jewelry (you have something valuable to steal), wearing restrictive clothing (unable to run or defend yourself), carrying more than you can manage, not paying attention to what’s going on around you,  leaving valuables in plain sight, leaving your garage open, flashing money (best not to show everything in your wallet when trying to pay) are all ways to make yourself a target.";

        safetyTips[10][0] = "Lock up your purse";
        safetyTips[10][1] = "Get a carabiner from the sporting goods section. Use it to wrap your shoulder strap through a shopping cart a few times (because the safety strap is probably being used for your child), then use the clip to attach the looped end to the cart. It’s a quick snap-in, and easy lever to remove when you’re ready to go. You can also do this in a restaurant as well. Just make sure your carabiner isn’t weak, but you don’t have to get an industrial sized one. You can even get cute ones in shapes of hearts or houses or dog bones, etc. (but make sure they aren’t super lightweight). You can store the carabiner on the strap, and it will only take you about 12 seconds to strap your bag to the cart or chair. 12 seconds. That’s a micro-fraction of the time that you stand looking at what flavor of yogurt to buy.";

        safetyTips[11][0] = "Don’t carry a purse";
        safetyTips[11][1] = "Consider not carrying a purse (I know  – super radical, and odd coming from someone who believes in carrying a day bag), but in all situations, do you absolutely need to carry in a purse to the store? Is your wallet so huge that you can’t tuck it into your pocket? Do you really need your lipstick and brush and makeup bag and all the other stuff for quick runs into Target? There may be times when leaving it in the trunk locked up is a better idea. Just consider it. I carry my wallet and my car keys on my body. I do not keep them in my day bag. That way, if ever I walk off and forget my bag, someone happens to cut the strap and steal it, my money, my identity and my means of escape are on me, not with them.  I hear your argument that your clothes don’t have pockets, etc. To me, safety is infinitely more important than fashion (and coincidentally, my husband agrees!)";

        safetyTips[12][0] = "";
        safetyTips[12][1] = "Don’t leave your purse in plain sight in your car. Carjackings/burglaries are on the rise, and many times it’s just a hit and run – knock out the glass, reach in to grab a purse and run. Keep your purse out of plain site instead of leaving it on the car seat next to you.";
    }

    public static void getRandomTip(){
        setAllTips();
        Random rand = new Random();

        generatedRandomNum = rand.nextInt(13) + 1;
    }

    public static String[] returnDetails(){
        getRandomTip();
        return safetyTips[generatedRandomNum];
    }

}
