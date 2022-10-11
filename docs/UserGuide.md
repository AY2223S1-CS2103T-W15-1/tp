---
layout: page
title: User Guide
---

# User Guide

SoConnect is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, SoConnect can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `soconnect.jar` from [here](https://github.com/AY2223S1-CS2103T-W15-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your SoConnect.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Sort Contacts : `sort`

Sort the list of contacts displayed by certain parameter(s).

Default sorting orders:
* Alphabetical order for *names*, *emails*, *addresses*.
* Increasing order for *phone numbers*.
* Contacts that have a specified *tag* appear before those without the *tag*.

Format: `sort [n/] [p/] [e/] [a/] [t/TAG]…​`
* To sort in reverse order, use these modified parameters: `[n/!] [p/!] [e/!] [a/!] [t/!TAG]`.
* To sort with multiple parameters, arrange the parameters in order of decreasing priority.

Example:
* `sort n/` sorts by names. (E.g. `David` appears before `Mike`)
* `sort t/!friend` sorts by the `friend` tag in reverse. (E.g. `Mike` appears before `David` who has the `friend` tag)
* `sort t/friend n/` sorts by the `friend` tag first, followed by names. (E.g. `David` and `Fred` who have the `friend` tag appear before `Mike`, `David` appears before `Fred`)

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Create a Tag: `tag create`

Creates a new tag

Format: `tag create t/TAG`

Example:
* `tag create t/family` creates a `family` tag.

### Edit a tag: `tag edit`

Renames an existing tag.

Format: `tag edit t/TAG1 t/ TAG2`

* `TAG1` is the current name of the tag and `TAG2` is the new name of the tag.

Example:
* `tag edit t/friend t/bestFriend` changes the friend tag to a bestFriend tag.

### Add a Tag to a Contact: `tag add`

Adds an existing tag to an existing contact.

Format: `tag add n/NAME t/TAG`

* `NAME` is the name of the contact to add `TAG` to.

Example:
* `tag add n/John Doe t/friend` adds the friend tag to John Doe.

###  Search for a Contact: `search`

Search for contacts using tags or other information.

Format: `search [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…`

* `search t/TAG1 and t/TAG2…` searches for contacts that contains all given tags.
* `search t/TAG1 or t/TAG2…` searches for contacts that contain any of the given tags.
* The search using `n/NAM`E is case-insensitive. e.g `hans` will match `Hans`.
* Displays a list of relevant contacts related to the search query if no search result available.

Example:
* `search t/family` returns all people tagged with family in the contact list.
* `search p/86178789` returns all contacts with that phone number.
* `search t/cs2103t and t/tp` returns all contacts tagged with both cs2103t and tp.
* `search t/friends or t/family` returns all contacts tagged with either friends or family.
* `search n/Johm` is supposed to return an empty result since there is no person named `Johm` in the contact list, but now it will return people with names similar to that, for example, `John`.

### Autocomplete: `search`

Show a list of names or tags that match the current search query.
This feature is only available when searching for names or tags.

Format: `search [n/NAME_PREFIX]`, `search [t/TAG_PREFIX]`

* Only names or tags with their prefix that match the current name or tag search query will be returned. E.g. `An` will match `Angel` but not `Jordan`.
* The search is case-insensitive. E.g `hans` will match `Hans`.

Example:
* `search n/An` will show a list of names that start with `An`.
* `search t/fa` will show a list of tags that start with `fa`.

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Hide Contact Details: `customise hide`

Hide certain information of all contacts shown.

Format: `customise hide [p/] [e/] [a/] [t/]`

* Hides the information specified.
* If the information specified is already hidden, it will stay hidden.

Example:
* `customise hide e/` The application no longer shows emails in the contact list.

### Show Contact Details: `customise show`

Show certain information of all contacts shown.

Format: `customise show [p/] [e/] [a/] [t/]`

* Shows the information specified.
* If the information specified is already shown, it will stay shown.

Example:
* `customise show a/` The application now shows addresses in the contact list.

### Customise Order of Details: `customise order`

Customise order of information shown in all contacts shown.

Format: `customise order [p/] [e/] [a/] [t/]`

*Attributes not given will be ordered last in default order. (tag > phone > address > email)

Example:

* `customise order a/ e/ p/` The application now shows address first, followed by email, phone, then tags.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

SoConnect data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

SoConnect data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, SoConnect will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action         | Format, Examples                                                                                                                                                      |
|----------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**        | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Clear**      | `clear`                                                                                                                                                               |
| **Customise**  | `customise hide [p/] [e/] [a/] [t/]` <br> `customise show [p/] [e/] [a/] [t/]` <br> `customise order [p/] [e/] [a/] [t/]` <br> e.g, `customise order a/ e/ p/`        |
| **Delete**     | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                   |
| **Edit**       | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                           |
| **Search**     | `search [n/NAME] [p/PHONE_NUMBER] ...`<br> e.g., `seach n/John Doe t/cs2103t`                                                                                         |
| **List**       | `list`                                                                                                                                                                |
| **Sort**       | `sort [n/] [p/] [e/] [a/] [t/TAG]…​` <br> e.g., `sort t/!friend n/`                                                                                                   |
| **Help**       | `help`                                                                                                                                                                |
| **Create Tag** | `tag create t/TAG` <br> e.g., `tag create t/friend`                                                                                                                   |
| **Edit Tag**   | `tag edit t/TAG1 t/TAG2`  <br> e.g., `tag edit t/friend t/bestFriend`                                                                                                 |
| **Add Tag**    | `tag add n/NAME t/TAG` <br> e.g., `tag add n/John Doe t/friend`                                                                                                       |
