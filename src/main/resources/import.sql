insert into restaurante
(id, nome, taxa_frete, cozinha_id, data_cadastro,
data_atualizacao, endereco_cidade_id, endereco_cep,
endereco_logradouro, endereco_numero, endereco_complemento,
endereco_bairro) values
(
        default, "Letabela", 3.45, 2, utc_timestamp, utc_timestamp, 1, "38610-009", "Prefeito Joao Costa",
        "123", "Rua Vereador Joao Narciso", "Barroca");
);