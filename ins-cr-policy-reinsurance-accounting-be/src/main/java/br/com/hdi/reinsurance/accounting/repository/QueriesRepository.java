package br.com.hdi.reinsurance.accounting.repository;

public class QueriesRepository {
    public static final String SELECT_CONTABILIZACAO = "SELECT top 5 CPR_CONTRATO\n" +
            "\t,CPR_EMPRESA\n" +
            "\t,CPR_SUCURSAL\n" +
            "\t,CPR_CARTEIRA\n" +
            "\t,CPR_RAMO_ITEM\n" +
            "\t,CPR_PARTICIPANTE\n" +
            "\t,CPR_UF\n" +
            "\t,CPR_MES\n" +
            "\t,CPR_ANO\n" +
            "\n" +
            "    ,SUM(PRMN_MA_FX_PMD * PRMN_MA_DST_PERC / 100 * CPR_PERCENTUAL) AS VR_PMD \n" +
            "\n" +
            "\n" +
            "\t,CCE_VR_PRODUCAO * CPR_PERCENTUAL\t\t\t\tAS VR_PRODUCAO\n" +
            "\t,CCE_VR_AJUSTAMENTO * CPR_PERCENTUAL\t\t\tAS VR_AJUSTAMENTO\n" +
            "\t,CCE_VR_ESTORNO * CPR_PERCENTUAL\t\t\t\tAS VR_ESTORNO\n" +
            "\t,CCE_VR_PROVISAO * CPR_PERCENTUAL\t\t\t\tAS VR_PROVISAO\n" +
            "\t,CCE_VR_OSC_CMB_PMD * CPR_PERCENTUAL\t\t\tAS VR_OSC_CMB_PMD\t\t\t--RS0389\n" +
            "\t,CCE_VR_OSC_CMB_AJUSTAMENTO * CPR_PERCENTUAL\tAS VR_OSC_CMB_AJUSTAMENTO\t--RS0389\n" +
            "FROM DBO.CONTABILIDADE_CONTRATO_EPI A WITH (NOLOCK)\n" +
            "INNER JOIN DBO.CONTRATO_RESSEGURO_MA B WITH (NOLOCK) \n" +
            "\tON a.CCE_CONTRATO = b.CTR_MA_CONTRATO\n" +
            "------------------------------------------------------------------------\n" +
            "-- O PMD deve seguir os valores da faixa na contabilização\n" +
            "INNER JOIN DBO.PREMIO_MIN_MA E WITH (NOLOCK)\n" +
            "\tON  B.CTR_MA_PRMN_MA_ID = E.PRMN_MA_ID\n" +
            "\tAND B.CTR_MA_PRMN_ANO\t= E.PRMN_MA_ANO\n" +
            "\tAND  E.PRMN_MA_VERSAO = (\n" +
            "\t\tSELECT MAX(PRMN_MA_VERSAO)\n" +
            "\t\tFROM PREMIO_MIN_MA F\n" +
            "\t\tWHERE F.PRMN_MA_ID = E.PRMN_MA_ID\n" +
            "\t\t  AND F.PRMN_MA_ANO = E.PRMN_MA_ANO\n" +
            "\t\t)\n" +
            "INNER JOIN DBO.PREMIO_MIN_MA_FAIXA G WITH (NOLOCK)\n" +
            "ON\t\tPRMN_MA_ID\t\t\t= G.PRMN_MA_FX_PRMMA_ID\n" +
            "\tAND PRMN_MA_ANO\t\t\t= G.PRMN_MA_FX_PRMMA_ANO\n" +
            "\tAND PRMN_MA_VERSAO\t\t= G.PRMN_MA_FX_PRMMA_VERSAO\n" +
            "INNER JOIN DBO.PREMIO_MIN_MA_DISTR H WITH (NOLOCK)\n" +
            "ON\t\tPRMN_MA_FX_PRMMA_ID\t\t\t= H.PRMN_MA_DST_PRMMA_FX_PRMMA_ID\n" +
            "\tAND PRMN_MA_FX_PRMMA_ANO\t\t= H.PRMN_MA_DST_PRMMA_FX_PRMMA_ANO\n" +
            "\tAND PRMN_MA_FX_PRMMA_VERSAO\t\t= H.PRMN_MA_DST_PRMMA_FX_PRMMA_VER\n" +
            "\tAND PRMN_MA_FX_FAIXA\t\t\t= H.PRMN_MA_DST_PRMMA_FX_FAIXA\n" +
            "----------------------------------------------------------------------\n" +
            "INNER JOIN DBO.RATEIO_CONTRATO C WITH (NOLOCK) \n" +
            "\tON  a.CCE_CONTRATO\t= c.CPR_CONTRATO\n" +
            "\tAND a.CCE_MES\t\t= c.CPR_MES\n" +
            "\tAND a.CCE_ANO\t\t= c.CPR_ANO\n" +
            "WHERE \t  ((    CPR_ANO = 2020\n" +
            "\t\tAND CPR_MES\t = 05\n" +
            "\t   )\n" +
            "\tOR ( CPR_ANO\t= 2020\n" +
            "\t\tAND CPR_MES = 4\n" +
            "\t   ))\n" +
            "GROUP BY CPR_CONTRATO\n" +
            "\t,CPR_EMPRESA\n" +
            "\t,CPR_SUCURSAL\n" +
            "\t,CPR_CARTEIRA\n" +
            "\t,CPR_RAMO_ITEM\n" +
            "\t,CPR_PARTICIPANTE\n" +
            "\t,CPR_UF\n" +
            "\t,CPR_MES\n" +
            "\t,CPR_ANO\n" +
            "\t,CCE_VR_PRODUCAO * CPR_PERCENTUAL\t\t\t\t\n" +
            "\t,CCE_VR_AJUSTAMENTO * CPR_PERCENTUAL\t\t\t\n" +
            "\t,CCE_VR_ESTORNO * CPR_PERCENTUAL\t\t\t\t\n" +
            "\t,CCE_VR_PROVISAO * CPR_PERCENTUAL\t--\t\t\t\n" +
            "\t,CCE_VR_OSC_CMB_PMD * CPR_PERCENTUAL\t\t\t\n" +
            "\t,CCE_VR_OSC_CMB_AJUSTAMENTO * CPR_PERCENTUAL";
    public static final String SELECT_EVENTCODE = "SELECT count(*) FROM [DB-ConciliaContabil]..tbSCCRoteiroContabil where cdEventoContabil = ?";
    public static final String SELECT_OPERCONTABIL = "SELECT count(*) FROM [DB-ConciliaContabil]..tbSCCOperacaoContabil where cdOperacaoContabil = ?";
    public static final String SELECT_GRUPO = "select top 1 * from (select 0 cdGrupoLancamentoContabil union all select cdGrupoLancamentoContabil from [DB-ConciliaContabil]..tbSCCGrupoLancamentoContabil where cdTipoDocumento = ? and cdEmpresa = ? and cdSucursal = ? and cdCarteira = ? and nrApolice = ? and nrDocumentoOrigem = ? and nrEndosso = ? and nrFatura = ? and cdCongenere = ? and cdSucursalCongenere = ?) t order by 1 desc";
    public static final String SELECT_SCRIPT = "select a.cdContaContabil, a.cdRoteiroContabil, a.cdSubEvento, a.cdOperacaoContabil, a.cdTextoPadraoContabil, b.dsTextoPadraoContabil, a.cdTipoOperacao, c.icAgrupaContabilizacao from [DB-ConciliaContabil]..tbSCCRoteiroContabil a,[DB-ConciliaContabil]..tbSCCTextoPadraoContabil b, [DB-ConciliaContabil]..tbSCCEventoContabil c where a.cdEventoContabil = ? and a.cdTextoPadraoContabil = b.cdTextoPadraoContabil and a.cdEventoContabil = c.cdEventoContabil ";

    public static final String SELECT_NUMSEQCT = "DECLARE @nNumSeqCt NUMERIC, @pUser_Id VARCHAR(12) SELECT TOP 1 @pUser_Id = user_id from segcen..vw_bsusuario where user_id like 'U_SAIS_CIP%' EXECUTE SEGCEN..sp_usr_gera_seqct @pUser_Id, 'CT', @nNumSeqCt OUTPUT SELECT @nNumSeqCt num_seqct";

    public static final String INSERT_BEGIN = "BEGIN TRAN";
    public static final String INSERT_GRUPO = "declare @ncdGrupo numeric(18) = 0 select @ncdGrupo = isnull(max(cdGrupoLancamentoContabil),0)+1 from [DB-ConciliaContabil]..tbSCCGrupoLancamentoContabil insert into [DB-ConciliaContabil]..tbSCCGrupoLancamentoContabil (cdGrupoLancamentoContabil, cdEmpresa, cdSucursal, cdCarteira, nrApolice, nrDocumentoOrigem, cdTipoDocumento, nrEndosso, nrFatura, cdCongenere, cdSucursalCongenere) values (@ncdGrupo, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) select @ncdGrupo";
    public static final String INSERT_DETALHE = "declare @ncdDetalheGr numeric(18) = 0 select @ncdDetalheGr = isnull(max(cdDetalheLancamentoContabil),0)+1 from [DB-ConciliaContabil]..tbSCCDetalheLancamentoContabil insert into [DB-ConciliaContabil]..tbSCCDetalheLancamentoContabil (cdEventoContabil, cdContaContabil, cdRoteiroContabil, cdGrupoLancamentoContabil, cdDetalheLancamentoContabil, cdUsuarioAcaoInformacao, cdServicoAcaoInformacao, dtAcaoInformacao, cdAcaoInformacao, icRegistroAtivo, dtLancamentoContabil, vlLancamentoContabil, cdRamoSeguro, cdCentroCusto) values (?, ?, ?, ?, @ncdDetalheGr, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_CTLANC = "insert into segcen..ctlanc (cod_empresa, cod_sucursal, num_seqct, cod_sistema, dat_lanc, cod_lote, cod_evento, cod_subevent, num_ctact, sta_lanc, cod_historic, des_historic, cod_ccusto, cod_instal, vlr_lanc, flg_matriz, flg_sumariza, cod_carteira, cod_banco, cod_congener, num_seqdocum, num_seqendos, num_seqfatur, num_sinistro, cod_ramo, cod_ramoctb, tip_origem, cod_agrupador) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_CTLANCSUM = "insert into segcen..ctlancsum (num_seqct, dat_lanc, cod_lote, cod_evento, num_ctact, sta_lanc, cod_historic, des_historic, cod_ccusto, cod_instal, vlr_lancsum, tip_lanc, cod_subevent, cod_empresa, cod_sucursal, tip_origem, des_refer, cod_carteira, cod_banco, cod_congener, num_seqdocum, num_seqendos, num_seqfatur, num_sinistro, cod_ramo, cod_ramoctb, dat_registro) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_COMMIT = "COMMIT";
    public static final String INSERT_ROLLBACK = "IF @@TRANCOUNT > 0 ROLLBACK;";

}
