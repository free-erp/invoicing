/*
 * Copyright 2013, TengJianfa , and other individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.free_erp.client.ui.util;

/**
 *
 * @author afa
 */
public class PermissionUtilities
{
    //1为保留位,如果某个功能有此
    private static final int FILTER  = 1;
    private static final int FILTER_AND = 65534; //FFf-11111110
    private static final int ADD_FILTER = 1 << 1; // 01

    private static final int EDIT_FILTER = 1 << 2;
    private static final int REMOVE_FILTER = 1 << 3;
    private static final int PRINT_FILTER = 1 << 4;
    private static final int CHECK_FILTER = 1 << 5;
    private static final int DRAFT_FILTER = 1 << 6;
    private static final int EXPORT_FILTER = 1 << 7;
    private static final int  IMPORT_FILTER = 1 << 8;
    private static final int  ADDTYPE_FILTER= 1 << 9;
    private static final int  EDITTYPE_FILTER= 1 << 10;
    private static final int  REMOVETYPE_FILTER= 1 << 11;

    public static boolean hasModulePermission(int permission)
    {
        return (permission & FILTER) != 0;
    }

    public static int setModulePermission(int permission, boolean has)
    {
        if (has)
        {
            return (permission | FILTER);
        }
        else
        {
            return (permission & FILTER_AND);
        }
    }

    public static boolean hasAddPermission(int permission)
    {
        return (permission & ADD_FILTER) != 0;
    }

    public static boolean hasEditPermission(int permission)
    {
        return (permission & EDIT_FILTER) != 0;
    }

    public static boolean hasRemovePermission(int permission)
    {
        return (permission & REMOVE_FILTER) != 0;
    }

    public static boolean hasPrintPermission(int permission)
    {
        return (permission & PRINT_FILTER) != 0;
    }

    public static boolean hasCheckPermission(int permission)
    {
        return (permission & CHECK_FILTER) != 0;
    }

    public static boolean hasDraftPermission(int permission)
    {
        return (permission & DRAFT_FILTER) != 0;
    }

    public static boolean hasExportPermission(int permission)
    {
        return (permission & EXPORT_FILTER) != 0;
    }

    public static boolean hasImportPermission(int permission)
    {
        return (permission & IMPORT_FILTER) != 0;
    }
    public static boolean hasAddTypePermission(int permission)
    {
         return (permission & ADDTYPE_FILTER) != 0;
    }
       public static boolean hasRemoveTypePermission(int permission)
    {
         return (permission & REMOVETYPE_FILTER) != 0;
    }

         public static boolean hasEditypePermission(int permission)
    {
         return (permission & EDITTYPE_FILTER) != 0;
    }
    public static int setAddPermission(int permission)
    {
        return (permission | ADD_FILTER);
    }

    public static int setAddPermission(int permission, boolean has)
    {
        if (has)
        {
            return (permission | ADD_FILTER);
        }
        else
        {
            return (permission & ~ADD_FILTER);
        }
    }

    public static int setEditPermission(int permission)
    {
       return (permission | EDIT_FILTER);
    }

    public static int setEditPermission(int permission, boolean has)
    {
        if (has)
        {
            return (permission | EDIT_FILTER);
        }
        else
        {
            return (permission & ~EDIT_FILTER);
        }
    }


    public static int setRemovePermission(int permission)
    {
        return (permission | REMOVE_FILTER);
    }

    public static int setRemovePermission(int permission, boolean has)
    {
        if (has)
        {
            return (permission | REMOVE_FILTER);
        }
        else
        {
            return (permission & ~REMOVE_FILTER);
        }
    }


    public static int setPrintPermission(int permission)
    {
        return (permission | PRINT_FILTER);
    }

    public static int setPrintPermission(int permission, boolean has)
    {
        int v = permission;
        if (has)
        {
            v = permission | PRINT_FILTER;
            
        }
        else
        {
            v = permission & ~PRINT_FILTER;
        }
        return v;
    }

    public static int setCheckPermission(int permission)
    {
        return (permission | CHECK_FILTER);
    }

    public static int setCheckPermission(int permission, boolean has)
    {
        if (has)
        {
            return (permission | CHECK_FILTER);
        }
        else
        {
            return (permission & ~CHECK_FILTER);
        }
    }

    public static int setDraftPermission(int permission)
    {
        return (permission | DRAFT_FILTER);
    }

    public static int setDraftPermission(int permission, boolean has)
    {
        if (has)
        {
            return (permission | DRAFT_FILTER);
        }
        else
        {
            return (permission & ~DRAFT_FILTER);
        }
    }

    public static int setExportPermission(int permission)
    {
        return (permission | EXPORT_FILTER);
    }

    public static int setExportPermission(int permission, boolean has)
    {
        if (has)
        {
            return (permission | EXPORT_FILTER);
        }
        else
        {
            return (permission & ~EXPORT_FILTER);
        }
    }

    public static int setImportPermission(int permission)
    {
        return (permission | IMPORT_FILTER);
    }

    public static int setImportPermission(int permission, boolean has)
    {
        if (has)
        {
            return (permission | IMPORT_FILTER);
        }
        else
        {
            return (permission & ~IMPORT_FILTER);
        }
    }

      public static int setAddTypePermission(int permission)
    {
         return (permission | ADDTYPE_FILTER);
    }

      public static int setAddTypePermission(int permission, boolean has)
    {
        if (has)
        {
            return (permission | ADDTYPE_FILTER);
        }
        else
        {
            return (permission & ~ADDTYPE_FILTER);
        }
    }

       public static int setRemoveTypePermission(int permission)
    {
         return (permission | REMOVETYPE_FILTER);
    }

    public static int setRemoveTypePermission(int permission, boolean has)
    {
        if (has)
        {
            return (permission | REMOVETYPE_FILTER);
        }
        else
        {
            return (permission & ~REMOVETYPE_FILTER);
        }
    }

         public static int setEditypePermission(int permission)
    {
         return (permission | EDITTYPE_FILTER);
    }

     public static int setEditypePermission(int permission, boolean has)
    {
        if (has)
        {
            return (permission | EDITTYPE_FILTER);
        }
        else
        {
            return (permission & ~EDITTYPE_FILTER);
        }
    }

    public static void main(String args[])
    {
        int a = 1;
        a = setRemoveTypePermission(a);
        System.out.println("value:" + a + "   r:" + REMOVE_FILTER);

    }
}
