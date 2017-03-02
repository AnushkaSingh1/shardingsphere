/*
 * Copyright 1999-2101 Alibaba Group Holding Ltd.
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

package com.alibaba.druid.sql.ast.statement;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLObjectImpl;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class SQLSelectQueryBlock extends SQLObjectImpl implements SQLSelectQuery {
    
    private boolean distinct;
    
    private SQLTableSource from;
    
    private SQLExprTableSource into;
    
    private SQLExpr where;
    
    private SQLSelectGroupByClause groupBy;
    
    private final List<SQLSelectItem> selectList = new ArrayList<>();
    
    public void setInto(final SQLExpr into) {
        setInto(new SQLExprTableSource(into));
    }
    
    public void setInto(final SQLExprTableSource into) {
        if (null != into) {
            into.setParent(this);
        }
        this.into = into;
    }
    
    public void setWhere(final SQLExpr where) {
        if (null != where) {
            where.setParent(this);
        }
        this.where = where;
    }
    
}
