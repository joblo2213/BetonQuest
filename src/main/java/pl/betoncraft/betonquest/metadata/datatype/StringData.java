/**
 * BetonQuest - advanced quests for Bukkit
 * Copyright (C) 2018  Jakub "Co0sh" Sapalski
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pl.betoncraft.betonquest.metadata.datatype;

import java.util.Map;
import java.util.Optional;

/**
 * A simple string based data type.
 *
 * @author Jakub Sapalski
 */
public class StringData extends VariableData<StringData> {

    protected Optional<Character> spaceCharacter = Optional.empty();

    @Override
    public Type getType() {
        return Type.STRING;
    }

    /**
     * @param spaceCharacter
     *                           an optional character which will be replaced with a
     *                           space when parsing instructions
     * @return this object
     */
    public StringData setSpaceCharacter(char spaceCharacter) {
        this.spaceCharacter = Optional.of(spaceCharacter);
        return this;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> results = super.serialize();

        spaceCharacter.ifPresent(value -> results.put("spaceCharacter", String.valueOf(value)));

        return results;
    }

}