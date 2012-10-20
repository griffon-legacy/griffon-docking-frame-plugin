/*
 * Copyright 2009-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Per Junel
 * @author Christoph Lipp
 * @author Alexander Klein
 */
class DockingFrameGriffonPlugin {
    // the plugin version
    String version = '1.0.0'
    // the version or versions of Griffon the plugin is designed for
    String griffonVersion = '1.0.0 > *'
    // the other plugins this plugin depends on
    Map dependsOn = [swing: '1.0.0']
    // resources that are included in plugin packaging
    List pluginIncludes = []
    // the plugin license
    String license = 'Apache Software License 2.0'
    // Toolkit compatibility. No value means compatible with all
    // Valid values are: swing, javafx, swt, pivot, gtk
    List toolkits = ['swing']
    // Platform compatibility. No value means compatible with all
    // Valid values are:
    // linux, linux64, windows, windows64, macosx, macosx64, solaris
    List platforms = []
    // URL where documentation can be found
    String documentation = ''
    // URL where source can be found
    String source = 'https://github.com/griffon/griffon-docking-frames-plugin'

    List authors = [
        [
            name: 'Hackergarten',
            email: 'hackergarten@hackergarten.net'
        ],
        [
            name: 'Alexander Klein',
            email: 'info@aklein.org'
        ]
    ]
    String title = 'Docking windows/panels support'
    String description = '''
[DockingFrames][1] is an open source Java Swing docking framework that allows you to work with dockable frames.

Usage
-----

The following nodes will become available on a View script upon installing this plugin.
For all nodes, the id attribute is used as uid where appropriate.
If a node is a child of dockingArea, contentArea, gridArea and workingArea the attribute constraints will be used as an id for a
dynamically created dockable.

| Node                    | Property                       | Type                                                           | Default   | Required | Bindable | Notes                                                                                            |
| ----------------------- | ------------------------------ | -------------------------------------------------------------- | --------- | -------- | -------- | ------------------------------------------------------------------------------------------------ |
| dockingControl          | groupBehaviour                 | String or CGroupBehavior                                       |           | no       |          |                                                                                                  |
|                         | language                       | Locale                                                         |           | no       |          |                                                                                                  |
|                         | maximizeArea                   | String                                                         |           | no       |          | id of the area                                                                                   |
|                         | missingPerspectiveStrategy     | MissingPerspectiveStrategy                                     |           | no       |          |                                                                                                  |
|                         | missingStrategy                | MissingCDockableStrategy                                       |           | no       |          |                                                                                                  |
|                         | preferenceModel                | PreferenceModel                                                |           | no       |          |                                                                                                  |
|                         | revertToBasicModes             | boolean                                                        |           | no       |          |                                                                                                  |
|                         | theme                          | String                                                         |           | no       |          | basic, bubble, eclipse, flat, smooth                                                             |
| dockingArea             | all as javax.swing.JPanel      |                                                                |           |          |          |                                                                                                  |
|                         | control                        | CControlAccess                                                 |           | no       |          |                                                                                                  |
|                         | minimumAreaSize                | List or Dimension                                              |           | no       |          |                                                                                                  |
|                         | perspective                    | String                                                         | 'default' | no       |          | name of the perspective                                                                          |
| contentArea             | all as javax.swing.JPanel      |                                                                |           |          |          |                                                                                                  |
|                         | control                        | CControlAccess                                                 |           | no       |          |                                                                                                  |
|                         | minimumAreaSize                | List or Dimension                                              |           | no       |          |                                                                                                  |
|                         | perspective                    | String                                                         | 'default' | no       |          | name of the perspective                                                                          |
| minimizeArea            | all as javax.swing.JPanel      |                                                                |           |          |          |                                                                                                  |
|                         | control                        | CControlAccess                                                 |           | no       |          |                                                                                                  |
|                         | direction                      | String                                                         |           | no       |          | auto, north, south, east, west                                                                   |
| gridArea                |                                |                                                                |           |          |          |                                                                                                  |
|                         | control                        | CControlAccess                                                 |           | no       |          |                                                                                                  |
|                         | focusComponent                 | Component                                                      |           | no       |          |                                                                                                  |
|                         | maximizingArea                 | boolean                                                        |           | no       |          |                                                                                                  |
|                         | minimizedHold                  | boolean                                                        |           | no       |          |                                                                                                  |
|                         | minimizedHoldSwitchable        | boolean                                                        |           | no       |          |                                                                                                  |
|                         | minimizedSize                  | List or Dimension                                              |           | no       |          |                                                                                                  |
|                         | resizeLocked                   | boolean                                                        |           | no       |          |                                                                                                  |
|                         | resizeLockedHorizontally       | boolean                                                        |           | no       |          |                                                                                                  |
|                         | resizeLockedVertically         | boolean                                                        |           | no       |          |                                                                                                  |
|                         | singleTabShown                 | boolean                                                        |           | no       |          |                                                                                                  |
|                         | titleIcon                      | Icon                                                           |           | no       |          |                                                                                                  |
|                         | titleShown                     | boolean                                                        |           | no       |          |                                                                                                  |
|                         | titleText                      | String                                                         |           | no       |          |                                                                                                  |
|                         | visible                        | boolean                                                        |           | no       |          |                                                                                                  |
|                         | workingArea                    | CStation                                                       |           | no       |          |                                                                                                  |
| workingArea             |                                |                                                                |           |          |          |                                                                                                  |
|                         | control                        | CControlAccess                                                 |           | no       |          |                                                                                                  |
|                         | focusComponent                 | Component                                                      |           | no       |          |                                                                                                  |
|                         | maximizingArea                 | boolean                                                        |           | no       |          |                                                                                                  |
|                         | minimizedHold                  | boolean                                                        |           | no       |          |                                                                                                  |
|                         | minimizedHoldSwitchable        | boolean                                                        |           | no       |          |                                                                                                  |
|                         | minimizedSize                  | List or Dimension                                              |           | no       |          |                                                                                                  |
|                         | resizeLocked                   | boolean                                                        |           | no       |          |                                                                                                  |
|                         | resizeLockedHorizontally       | boolean                                                        |           | no       |          |                                                                                                  |
|                         | resizeLockedVertically         | boolean                                                        |           | no       |          |                                                                                                  |
|                         | singleTabShown                 | boolean                                                        |           | no       |          |                                                                                                  |
|                         | titleIcon                      | Icon                                                           |           | no       |          |                                                                                                  |
|                         | titleShown                     | boolean                                                        |           | no       |          |                                                                                                  |
|                         | titleText                      | String                                                         |           | no       |          |                                                                                                  |
|                         | visible                        | boolean                                                        |           | no       |          |                                                                                                  |
|                         | workingArea                    | CStation                                                       |           | no       |          |                                                                                                  |
| dockable                |                                |                                                                |           |          |          |                                                                                                  |
|                         | closeable                      | boolean                                                        |           | no       |          |                                                                                                  |
|                         | control                        | CControlAccess                                                 |           | no       |          |                                                                                                  |
|                         | externalizable                 | boolean                                                        |           | no       |          |                                                                                                  |
|                         | focusComponent                 | Component                                                      |           | no       |          |                                                                                                  |
|                         | layout                         | LayoutManager                                                  |           | no       |          |                                                                                                  |
|                         | maximizable                    | boolean                                                        |           | no       |          |                                                                                                  |
|                         | minimizable                    | boolean                                                        |           | no       |          |                                                                                                  |
|                         | minimizedHold                  | boolean                                                        |           | no       |          |                                                                                                  |
|                         | minimizedHoldSwitchable        | boolean                                                        |           | no       |          |                                                                                                  |
|                         | minimizedSize                  | List or Dimension                                              |           | no       |          |                                                                                                  |
|                         | resizeLocked                   | boolean                                                        |           | no       |          |                                                                                                  |
|                         | resizeLockedHorizontally       | boolean                                                        |           | no       |          |                                                                                                  |
|                         | resizeLockedVertically         | boolean                                                        |           | no       |          |                                                                                                  |
|                         | singleTabShown                 | boolean                                                        |           | no       |          |                                                                                                  |
|                         | stackable                      | boolean                                                        |           | no       |          |                                                                                                  |
|                         | titleIcon                      | Icon                                                           |           | no       |          |                                                                                                  |
|                         | titleShown                     | boolean                                                        |           | no       |          |                                                                                                  |
|                         | titleText                      | String                                                         |           | no       |          |                                                                                                  |
|                         | titleToolTip                   | String                                                         |           | no       |          |                                                                                                  |
|                         | visible                        | boolean                                                        |           | no       |          |                                                                                                  |
|                         | workingArea                    | CStation                                                       |           | no       |          |                                                                                                  |
| dockableFactory         | filter                         | bibliothek.util.Filter, String, String[], Collection&lt;String&gt; | no        |          |          |                                                                                                  |
|                         | data                           | Map                                                            |           | no       |          | a map containing uid-SingleCDockable-pairs or uid-Closure-pairs creating a SingleCDockable       |
| mvcGroupFactory         | filter                         | bibliothek.util.Filter, String, String[], Collection&lt;String&gt; | no        |          |          | dynamically creating SingleCDockables from mvcGroups reffered by their mvcName                   |
| multiFactory            | value                          | MultipleCDockable                                              | yes       |          |          | Just a wrapper around a given MultipleCDockable as value                                         |
| placeholder             |                                |                                                                | no        |          |          |                                                                                                  |
| multiPlaceholder        | factory                        | String                                                         | yes       |          |          | factory id                                                                                       |
|                         | layout                         | MultipleCDockableLayout                                        | yes       |          |          |                                                                                                  |
| rootMenuPiece           | all as javax.swing.JMenu       |                                                                | no        |          |          |                                                                                                  |
|                         | disableWhenEmpty               | boolean                                                        | no        |          |          |                                                                                                  |
| subMenuPiece            | all as javax.swing.JMenu       |                                                                | no        |          |          |                                                                                                  |
|                         | disableWhenEmpty               | boolean                                                        | no        |          |          |                                                                                                  |
| nodeMenuPiece           |                                |                                                                |           |          |          |                                                                                                  |
| separatingMenuPiece     |                                |                                                                |           |          |          |                                                                                                  |
|                         | bottomSeparator                | boolean                                                        | no        |          |          |                                                                                                  |
|                         | emptySeparator                 | boolean                                                        | no        |          |          |                                                                                                  |
|                         | topSeparator                   | boolean                                                        | no        |          |          |                                                                                                  |
| menuPiece (MenuPiece)   | value                          | MenuPiece                                                      | yes       |          |          |                                                                                                  |
| dockableListMenuPiece   | control                        | CControlAccess                                                 |           | no       |          |                                                                                                  |
| themeMenuPiece          | control                        | CControlAccess                                                 |           | no       |          |                                                                                                  |
|                         | themes                         | ThemeMap                                                       | no        |          |          |                                                                                                  |
|                         | transferTheme                  | boolean                                                        | no        |          |          |                                                                                                  |
| preferenceMenuPiece     | control                        | CControlAccess                                                 |           | no       |          |                                                                                                  |
|                         | model                          | PreferenceModel                                                | no        |          |          |                                                                                                  |
| lookAndFeelMenuPiece    | control                        | CControlAccess                                                 |           | no       |          |                                                                                                  |
| layoutMenuPiece         | control                        | CControlAccess                                                 |           | no       |          |                                                                                                  |
|                         | submenu                        | boolean                                                        | no        |          |          | List of layouts as submenu                                                                       |
| dockingAction (CAction) | CAction                        | yes                                                            |           |          |          |
| blankAction             |                                |                                                                |           |          |          |                                                                                                  |
| buttonAction            | accelerator                    | String or KeyStroke                                            |           |          |          |                                                                                                  |
|                         | disabledIcon                   | Icon                                                           | no        |          |          |                                                                                                  |
|                         | dropDownSelectable             | boolean                                                        | no        |          |          |                                                                                                  |
|                         | dropDownTriggerableNotSelected | boolean                                                        | no        |          |          |                                                                                                  |
|                         | dropDownTriggerableSelected    | boolean                                                        | no        |          |          |                                                                                                  |
|                         | enabled                        | boolean                                                        | no        |          |          |                                                                                                  |
|                         | icon                           | Icon                                                           | no        |          |          |                                                                                                  |
|                         | text                           | String                                                         | no        |          |          |                                                                                                  |
|                         | tooltip                        | String                                                         | no        |          |          |                                                                                                  |
|                         | closure                        | Closure                                                        | no        |          |          | triggered when clicked                                                                           |
|                         | listener                       | ActionListener                                                 | no        |          |          |                                                                                                  |
| checkBoxAction          | accelerator                    | String or KeyStroke                                            |           |          |          |                                                                                                  |
|                         | disabledIcon                   | Icon                                                           | no        |          |          |                                                                                                  |
|                         | dropDownSelectable             | boolean                                                        | no        |          |          |                                                                                                  |
|                         | dropDownTriggerableNotSelected | boolean                                                        | no        |          |          |                                                                                                  |
|                         | dropDownTriggerableSelected    | boolean                                                        | no        |          |          |                                                                                                  |
|                         | enabled                        | boolean                                                        | no        |          |          |                                                                                                  |
|                         | icon                           | Icon                                                           | no        |          |          |                                                                                                  |
|                         | text                           | String                                                         | no        |          |          |                                                                                                  |
|                         | tooltip                        | String                                                         | no        |          |          |                                                                                                  |
|                         | closure                        | Closure                                                        | no        |          |          | triggered when changed                                                                           |
| dropDownButtonAction    | accelerator                    | String or KeyStroke                                            |           |          |          |                                                                                                  |
|                         | disabledIcon                   | Icon                                                           | no        |          |          |                                                                                                  |
|                         | dropDownSelectable             | boolean                                                        | no        |          |          |                                                                                                  |
|                         | dropDownTriggerableNotSelected | boolean                                                        | no        |          |          |                                                                                                  |
|                         | dropDownTriggerableSelected    | boolean                                                        | no        |          |          |                                                                                                  |
|                         | enabled                        | boolean                                                        | no        |          |          |                                                                                                  |
|                         | icon                           | Icon                                                           | no        |          |          |                                                                                                  |
|                         | text                           | String                                                         | no        |          |          |                                                                                                  |
|                         | tooltip                        | String                                                         | no        |          |          |                                                                                                  |
| menuAction              | accelerator                    | String or KeyStroke                                            |           |          |          |                                                                                                  |
|                         | disabledIcon                   | Icon                                                           | no        |          |          |                                                                                                  |
|                         | dropDownSelectable             | boolean                                                        | no        |          |          |                                                                                                  |
|                         | dropDownTriggerableNotSelected | boolean                                                        | no        |          |          |                                                                                                  |
|                         | dropDownTriggerableSelected    | boolean                                                        | no        |          |          |                                                                                                  |
|                         | enabled                        | boolean                                                        | no        |          |          |                                                                                                  |
|                         | icon                           | Icon                                                           | no        |          |          |                                                                                                  |
|                         | text                           | String                                                         | no        |          |          |                                                                                                  |
|                         | tooltip                        | String                                                         | no        |          |          |                                                                                                  |
| popupAction             | accelerator                    | String or KeyStroke                                            |           |          |          |                                                                                                  |
|                         | disabledIcon                   | Icon                                                           | no        |          |          |                                                                                                  |
|                         | dropDownSelectable             | boolean                                                        | no        |          |          |                                                                                                  |
|                         | dropDownTriggerableNotSelected | boolean                                                        | no        |          |          |                                                                                                  |
|                         | dropDownTriggerableSelected    | boolean                                                        | no        |          |          |                                                                                                  |
|                         | enabled                        | boolean                                                        | no        |          |          |                                                                                                  |
|                         | icon                           | Icon                                                           | no        |          |          |                                                                                                  |
|                         | text                           | String                                                         | no        |          |          |                                                                                                  |
|                         | tooltip                        | String                                                         | no        |          |          |                                                                                                  |
|                         | buttonBehavior                 | String or ButtonBehavior                                       | no        |          |          |                                                                                                  |
|                         | closeOnFocusLost               | boolean                                                        | no        |          |          |                                                                                                  |
|                         | content                        | JComponent                                                     | no        |          |          |                                                                                                  |
|                         | menuBehavior                   | String or MenuBehavior                                         | no        |          |          |                                                                                                  |
| radioButtonAction       | accelerator                    | String or KeyStroke                                            |           |          |          |                                                                                                  |
|                         | disabledIcon                   | Icon                                                           | no        |          |          |                                                                                                  |
|                         | dropDownSelectable             | boolean                                                        | no        |          |          |                                                                                                  |
|                         | dropDownTriggerableNotSelected | boolean                                                        | no        |          |          |                                                                                                  |
|                         | dropDownTriggerableSelected    | boolean                                                        | no        |          |          |                                                                                                  |
|                         | enabled                        | boolean                                                        | no        |          |          |                                                                                                  |
|                         | icon                           | Icon                                                           | no        |          |          |                                                                                                  |
|                         | text                           | String                                                         | no        |          |          |                                                                                                  |
|                         | tooltip                        | String                                                         | no        |          |          |                                                                                                  |
|                         | closure                        | Closure                                                        | no        |          |          | triggered when changed                                                                           |
|                         | group                          | String or CRadioGroup                                          | no        |          |          |                                                                                                  |
| separatorAction         | type                           | String                                                         |           |          |          | '', 'menu' or 'title'                                                                            |
| systemAction            | name                           | String                                                         |           |          |          | minimize, maximize, normalize, externalize, unexternalize, unmaximize_externalized, close, hold |
|                         | replacement                    | CAction                                                        | no        |          |          |                                                                                                  |

The nodes that are children of dockingArea, contentArea, gridArea and workingArea the following properties are valid:

| Property         | Type                                    | Default              | Required | Bindable | Notes                                          |
| ---------------- | --------------------------------------- | -------------------- | -------- | -------- | ---------------------------------------------- |
| dock             | Collection or CContentArea or CGridArea | [0.0, 0.0, 1.0, 1.0] | no       |          | Collection in a form of [x, y, w, h]           |
| dockMinimized    | String or CMinimizedArea                |                      | no       |          | north, south, east, west                       |
| dockExternalized | Collection                              |                      | no       |          | in a form of []                                |
| dockState        | String or ExtendedMode                  |                      | no       |          | minimized, maximized, normalized, externalized |

The nodes that are children of dropDownButtonAction the following properties are valid:

| Property  | Type    | Default | Required | Bindable |
| --------- | ------- | ------- | -------- | -------- |
| selection | boolean |         | no       | no       |

### Example

        import java.awt.Color
 
        application(title: 'DockingFrameDemo',
                size: [800, 600],
                locationByPlatform: true,
                iconImage: imageIcon('/griffon-icon-48x48.png').image,
                iconImages: [imageIcon('/griffon-icon-48x48.png').image,
                        imageIcon('/griffon-icon-32x32.png').image,
                        imageIcon('/griffon-icon-16x16.png').image]) {
            borderLayout()
            dockingControl(theme: 'eclipse') {
                menuBar {
                    rootMenuPiece(text: 'Docking') {
                        subMenuPiece(text: 'Docks', disableWhenEmpty: true) {
                            dockableListMenuPiece()    // List of all closeable dockables
                        }
                        subMenuPiece(text: 'Options') {
                            subMenuPiece(text: 'Look&Feel / Theme') {
                                lookAndFeelMenuPiece() // List of all Look&Feels
                                separatingMenuPiece(topSeparator: true) {
                                    themeMenuPiece()   // List of all docking themes
                                }
                            }
                            subMenuPiece(text: 'Layout') {
                                layoutMenuPiece()     // Entries to load, save and delete layouts
                            }
                            preferenceMenuPiece()     // Docking preferences
                        }
                    }
                }
                dockingArea(id: 'area') {
                    dockable(id: 'dock1',
                            titleText: 'Dock 1',
                            dock: [0, 0, 20, 50], // relative bounds in normal mode
                            dockExternalized: [100, 100, 300, 300] // absolute bounds in externalized mode
                    ) { panel(background: Color.GREEN) { label('dock1')} }
                    dockable(id: 'dock2',
                            titleText: 'Dock 2',
                            dock: [0, 50, 20, 50] // relative bounds in normal mode
                    ) { panel(background: Color.RED) { label('dock2')} }
                    dockable(id: 'dock3',
                            titleText: 'Dock 3',
                            dock: [20, 0, 80, 20] // relative bounds in normal mode
                    ) { panel(background: Color.YELLOW) { label('dock3')} }
                    dockable(id: 'dock4',
                            titleText: 'Dock 4',
                            dock: [80, 20, 20, 60] // relative bounds in normal mode
                    ) { panel(background: Color.BLUE) { label('dock4')} }
                    dockable(id: 'dock5',
                            titleText: 'Dock 5',
                            dock: [20, 80, 80, 20], // relative bounds in normal mode
                            dockMinimized: 'south', // position when minimized
                            dockState: 'normalized' // initial mode
                    ) { panel(background: Color.ORANGE) { label('dock5')} }
                    dockable(id: 'dock6',
                            titleText: 'Dock 6',
                            closeable: true,       // dockable can be hidden
                            dock: [20, 20, 60, 60] // relative bounds in normal mode
                    ) { panel(background: Color.MAGENTA) { label('dock6')} }
                    dockable(id: 'dock7',
                            titleText: 'Dock 7',
                            closeable: true,       // dockable can be hidden
                            dock: [20, 20, 60, 60] // relative bounds in normal mode
                    ) { panel(background: Color.CYAN) { label('dock7')} }
                    dockable(id: 'dock8',
                            titleText: 'Dock 8',
                            dock: [0, 0, 20, 50],  // relative bounds in normal mode
                            dockMinimized: 'west', // position when minimized
                            dockState: 'minimized' // initial mode
                    ) { panel(background: Color.WHITE) { label('dock8')} }
                }
            }
        }

[1]: http://dock.javaforge.com/
'''
}
