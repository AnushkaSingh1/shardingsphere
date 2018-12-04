/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.core.parsing.antlr.filler.engine;

import io.shardingsphere.core.metadata.table.ShardingTableMetaData;
import io.shardingsphere.core.parsing.antlr.filler.SQLSegmentFiller;
import io.shardingsphere.core.parsing.antlr.sql.segment.SQLSegment;
import io.shardingsphere.core.parsing.antlr.sql.segment.expr.SubquerySegment;
import io.shardingsphere.core.parsing.parser.sql.SQLStatement;
import io.shardingsphere.core.parsing.parser.sql.dql.select.SelectStatement;
import io.shardingsphere.core.rule.ShardingRule;

/**
 * Subquery filler.
 * 
 * @author duhongjun
 */
public class SubqueryFiller implements SQLSegmentFiller{
    
    @Override
    public void fill(SQLSegment sqlSegment, SQLStatement sqlStatement, ShardingRule shardingRule, ShardingTableMetaData shardingTableMetaData) {
        SubquerySegment subquerySegment = (SubquerySegment) sqlSegment;
        SelectStatement selectStatement = (SelectStatement)sqlStatement;        
        SelectStatement subqueryStatement = new SelectStatement();
        subqueryStatement.setSql(selectStatement.getSql());
        selectStatement.setSubQueryStatement(subqueryStatement);
        if(subquerySegment.getSelectClauseSegment().isPresent()) {
            new SelectClauseFiller().fill(subquerySegment.getSelectClauseSegment().get(), subqueryStatement, shardingRule, shardingTableMetaData);
        }
        if(subquerySegment.getFromWhereSegment().isPresent()) {
            new FromWhereFiller().fill(subquerySegment.getFromWhereSegment().get(), subqueryStatement, shardingRule, shardingTableMetaData);
        }
        if(subquerySegment.getGroupBySegment().isPresent()) {
            new GroupByFiller().fill(subquerySegment.getGroupBySegment().get(), subqueryStatement, shardingRule, shardingTableMetaData);
        }
        if(subquerySegment.getOrderBySegment().isPresent()) {
            new OrderByFiller().fill(subquerySegment.getOrderBySegment().get(), subqueryStatement, shardingRule, shardingTableMetaData);
        }
    }
}
