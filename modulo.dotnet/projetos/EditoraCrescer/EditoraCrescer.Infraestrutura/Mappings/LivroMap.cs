﻿using EditoraCrescer.Infraestrutura.Entidades;
using System;
using System.Collections.Generic;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EditoraCrescer.Infraestrutura.Mappings
{
    public class LivroMap : EntityTypeConfiguration<Livro>
    {
        public LivroMap()
        {
            ToTable("Livro");
            HasKey(p => p.Isbn);
            HasRequired(p => p.Autor).WithMany().HasForeignKey(x => x.IdAutor);
            HasOptional(p => p.Revisor).WithMany().HasForeignKey(x => x.IdRevisor);
        }
    }
}
