Coding Challenge
Test
I used Junit testing to check my solutions to the problem. I used edge cases such as the inclusive ranges for port and ip, and testing values that should not be in the range of the port or ip in rules.

Design
The coding design I chose was to divide the problem in four categories, hence four sets. I divided the problem in inbounds udp, inbounds tcp, outgoing udp, and outgoing tcp because the rules can only fall in these categories and I only stored ports and ips regarding these categories because storing direction and protocol would have been redundant because of these sets uniquely identifies that. I used this strategy to check when I search if a packet should be accepted or rejected. I only searched in the set that corresponds to the package direction and protocol. Even though, in the worsecase it is still O(n) runtime, the average would be O(n/4) which is an improvement. The space complexity is O(n) because we store all the data from the file in sets.

Team
1.	Data Team
2.	Platform
3.	Policy

