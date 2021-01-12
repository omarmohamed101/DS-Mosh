
## What is a system call ?

<p> System call provides the services of the operating system to the user programs via Application Program Interface(API). It provides an interface between a process and operating system to allow user-level processes to request services of the operating system.
so all programs need resources must use system calls
</p>

### Services provided by the system call:
- Process creation and management
- Main memory management
- File Access, Directory and File system management
- Device handling(I/O)
- Protection
- Networking, etc.


### System call types:
- Process control: end, abort, create, terminate, allocate and free memory.
			   Ex: fork(), Exit(), Wait()
- File management: create, open, close, delete, read file etc.
			   Ex open(), read(), write(), close()
- Device management
			   Ex: read(), write()
- Information maintenance
			   Ex: getpid(), sleep()
- Communication
			   Ex: pipe()
			
			
### How to add a system Call to xv6 ?

You need to make changes to 5 files:</br>
- syscall.h
- syscall.c
- sysproc.c
- usys.S
- user.h

<b>Example: Adding system call returns the read system call count</b>

Firstly we set up the process structure appropriatly in `proc.h` in the struct proc ADD a new variable `int readcount` to hold the number of system calls for each created process. </br>
Then in `proc.c` file exactly in allocproc function we have to set our variable initially with zero, The function first search for UNUSED process in the process table then jumb to the found label, here where we will initiallize our variable `p->readcount = 0;` </br>

Now we are ready to start implementing the systeme call</br>

In `syscall.h` There is a number assigned to every system call. And there is initially 21 of them already defined </br>

Add this line at the end of the file: </br>

>    #define SYS_getreadcount    22

In `syscall.c` Add a pointer to the system call </br>
this file contains array of function pointer which use the number we assigned in syscall.h as a pointer to the system call which will be defined in differen file. so add this line in its appropriate position:</br>

>    [SYS_getreadcount]   sys_getreadcount

This means, when system call occurred with system call number 22, function pointed by function pointer sys_getreadcount will be called.</br>

The syscall function in this file called whenever system call is made so we have to check whether if the system call was a read system call then increase our counter.</br>
```
if (num == SYS_read)
	curproc->readcount = curproc->readcount + 1;
```

Last thing with this file is adding the function prototype so as to be able to define it in different place. So add this line </br>

>    extern int sys_getreadcount(void)

In `sysproc.c` we implement the system call function, we can acess the current process in this file using myproc().</br>
```
int
sys_getreadcount(void)
{
	return myproc()->readcount;
}
```
The above function returns the current value of the readcount variable </br>

In `usys.S` we add interface to make the userprogram able to call the system call </br>

>    SYSCALL(getreadcount)

Finally in `user.h` we add the function which will be called from the user program

>    int getreadcount(void);

Now we are done with the system call implementation and we need to make the userprogram to use the system call </br>
Make a new file called `readcount.c` 
```
#include "types.h"
#include "stat.h"
#include "user.h"

int
main(int argc, char *argv[])
{
	char buffer[512];
	int ptr = open("test.txt", 'r');

	read(ptr, buffer, 512);
	read(ptr, buffer, 512);
	read(ptr, buffer, 512);

	
	printf(1, "The number of read syscall: %d\n", getreadcount());


    exit();
}
```
this is some useless read calls to make sure that our new system call is working properly, Note that you have to create a test.txt for testing purpose.</br>
In order to add the user program open `Makefile` and in UPROGS add your program to the list `_readcount\` then add the file name with the files in the EXTRA section add `readcount.c` </br>
Now it's time for testing </br>

>    make qemu

>    readcount

you should now see `The number of read syscall: 3` And you are Done. :)











## Table of contents

- [Description](#Description)
- [Used Devices](#Used-Devices)
- [Circuit Diagram](#Circuit-Diagram)
- [Features](#Features)
- [Team members](#Team-members)

        
    

## Team members
- [Mohamed Fathy](https://github.com/Mohamed-Fathy-Salah)
- [Omar Mohamed](https://github.com/omarmohamed101)
- [Ahmed Nashaat](https://github.com/AhmadNashaat0)
- [Ahmed Yasser](https://github.com/ahmadyasser01)
- [Shahenda Hamdy](https://github.com/shahendahamdy)

